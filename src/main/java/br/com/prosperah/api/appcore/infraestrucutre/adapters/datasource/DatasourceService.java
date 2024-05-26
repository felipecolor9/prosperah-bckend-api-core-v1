package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.exceptions.EmailAlreadyExistsException;
import br.com.prosperah.api.appcore.exceptions.UsernameAlreadyExistsException;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.UserPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.CadastralRepository;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static br.com.prosperah.api.appcore.constants.Constants.*;
import static br.com.prosperah.api.appcore.utils.ConvertUtils.ToBytes;
import static br.com.prosperah.api.appcore.utils.GeneralUtils.generateRandomSixDigitNumber;
import static br.com.prosperah.api.appcore.utils.ValidationUtils.isValidEmail;
import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;

@Service
public class DatasourceService implements DatasourcePort {

    private static final Logger log = LoggerFactory.getLogger(DatasourceService.class);

    @Autowired
    CadastralRepository cadastralRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<CadastralUserPersistData> saveCadastralUser(CadastralUserPersistData cadUser) throws BadRequestException {
        var email = cadUser.getEmail();
        var username = cadUser.getUsername();
        if (isValidEmail(email) && isUserAvailable(username, email)) {

            var idTeste = randomUUID();
            System.out.println(idTeste); //TODO Para testes - Remover futuramente
            cadUser.setCodAuth(generateRandomSixDigitNumber());
            cadUser.setId(ToBytes(idTeste));
            cadastralRepository.save(cadUser);
            log.info(USUARIO_CRIADO, username);
            return Optional.of(cadUser);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserPersistData> saveUser(String clientId, String authCode, String sessionId, String userEmail) throws BadRequestException {
        var clientIdBytes = ToBytes(UUID.fromString(clientId));
        var userFound = cadastralRepository.findById(clientIdBytes).orElseThrow(BadRequestException::new);
        //TODO verificar AuthCode
        var userSaved = userRepository.save(initConsolidatedUser(clientIdBytes, userFound));

        return Optional.of(userSaved);
    }

    private boolean isUserAvailable(String username, String email) throws BadRequestException {
        if (cadastralRepository.existsByUsername(username) || userRepository.existsByUsername(username))
            throw new UsernameAlreadyExistsException();
        if (cadastralRepository.existsByEmail(email) || userRepository.existsByEmail(email))
            throw new EmailAlreadyExistsException();
        return true;
    }

    private static UserPersistData initConsolidatedUser(byte[] clientIdBytes, CadastralUserPersistData userFound) {
        return UserPersistData.builder()
                .id(ToBytes(randomUUID()))
                .cadastralUser(clientIdBytes)
                .username(userFound.getUsername())
                .email(userFound.getEmail())
                .fullName(userFound.getFullName())
                .password(userFound.getPassword())
                .creationDate(userFound.getCreationDate())
                .validationDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
}
