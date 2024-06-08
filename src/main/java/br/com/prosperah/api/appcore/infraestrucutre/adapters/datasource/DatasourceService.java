package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.domain.LoginUserForm;
import br.com.prosperah.api.appcore.exceptions.EmailAlreadyExistsException;
import br.com.prosperah.api.appcore.exceptions.UserNotFoundException;
import br.com.prosperah.api.appcore.exceptions.UsernameAlreadyExistsException;
import br.com.prosperah.api.appcore.exceptions.WrongAuthCodeException;
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
import static java.lang.Integer.parseInt;
import static java.util.Optional.empty;
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
        if (isValidEmail(email) && isCadastralUserAvailable(username, email)) {

            var idTeste = randomUUID();
            System.out.println(idTeste);
            cadUser.setCodAuth(generateRandomSixDigitNumber());
            cadUser.setId(ToBytes(idTeste));
            cadastralRepository.save(cadUser);
            log.info(USUARIO_CRIADO, username);
            return Optional.of(cadUser);
        }
        return empty();
    }

    @Override
    public Optional<UserPersistData> saveConsolidatedUser(String clientId, String authCode, String sessionId, String userEmail) throws BadRequestException, UserNotFoundException {
        var clientIdBytes = ToBytes(UUID.fromString(clientId));
        var userFound = cadastralRepository.findById(clientIdBytes).orElseThrow(UserNotFoundException::new);

        if (isConsolidatedUserAvailable(userFound.getUsername(), userFound.getEmail()) && verifyAuthCode(parseInt(authCode)))
            return Optional.of(userRepository.save(initConsolidatedUser(clientIdBytes, userFound)));

        return empty();
    }

    @Override
    public Optional<UserPersistData> findAndLogUser(LoginUserForm form) {
        return userRepository.findByUsernameAndPassword(form.getUsername(), form.getPassword());
    }


    private boolean isCadastralUserAvailable(String username, String email) throws BadRequestException {
        if (cadastralRepository.existsByUsername(username) || userRepository.existsByUsername(username))
            throw new UsernameAlreadyExistsException();
        if (cadastralRepository.existsByEmail(email) || userRepository.existsByEmail(email))
            throw new EmailAlreadyExistsException();
        return true;
    }

    private boolean isConsolidatedUserAvailable(String username, String email) throws BadRequestException {
        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email))
            throw new UsernameAlreadyExistsException();
        return true;
    }

    private boolean verifyAuthCode(int authCode) throws WrongAuthCodeException {
        if (!(cadastralRepository.existsByCodAuth(authCode))) throw new WrongAuthCodeException();
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
