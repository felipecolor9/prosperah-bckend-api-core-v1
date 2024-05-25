package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.exceptions.EmailAlreadyExistsException;
import br.com.prosperah.api.appcore.exceptions.UsernameAlreadyExistsException;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.CadastralRepository;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.prosperah.api.appcore.constants.Constants.*;
import static br.com.prosperah.api.appcore.utils.GeneralUtils.generateRandomSixDigitNumber;
import static br.com.prosperah.api.appcore.utils.ValidationUtils.isValidEmail;

@Service
public class DatasourceService implements DatasourcePort {

    private static final Logger log = LoggerFactory.getLogger(DatasourceService.class);

    @Autowired
    CadastralRepository cadastralRepository;

    @Autowired
    UserRepository userRepository;

    public boolean saveCadastralUser(CadastralUserPersistData cadUser) throws BadRequestException {
        var email = cadUser.getEmail();
        var username = cadUser.getUsername();
        if (isValidEmail(email) && isUserAvailable(username, email)) {
            //TODO - impedir que seja criado um novo cadastro com o mesmo uuid (atualmente salva e
            // sobreescreve o usuario anterior mesmo que passe por todas as validacoes)
            // obs: gerar uuid nao é responsabilidade do usuário
            cadUser.setCodAuth(generateRandomSixDigitNumber());
            cadastralRepository.save(cadUser);
            log.info(String.format(USUARIO_CRIADO, username));
            return true;
        }
        return false;
    }

    private boolean isUserAvailable(String username, String email) throws BadRequestException {
        if (cadastralRepository.existsByUsername(username) || userRepository.existsByUsername(username))
            throw new UsernameAlreadyExistsException();
        if (cadastralRepository.existsByEmail(email) || userRepository.existsByEmail(email))
            throw new EmailAlreadyExistsException();
        return true;
    }
}
