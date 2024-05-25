package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.CadastralUserPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.UserPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.repository.CadastralRepository;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.repository.UserRepository;
import br.com.prosperah.api.appcore.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Optional;

import static br.com.prosperah.api.appcore.Constants.*;
import static br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.CadastralUserPersistData.toPersistData;
import static br.com.prosperah.api.appcore.utils.ValidationUtils.isEmailValid;
import static java.util.Optional.empty;

@Service
public class DatasourceService implements DatasourcePort {

    private static final Logger log = LoggerFactory.getLogger(DatasourceService.class);

    @Autowired
    CadastralRepository cadastralRepository;

    @Autowired
    UserRepository userRepository;

    public boolean saveCadastralUser(CadastralUserPersistData cadUser) {
        var email = cadUser.getEmail();
        var username = cadUser.getUsername();

        if (isUserAvailable(username, email) && isEmailValid(email) ) {
            cadastralRepository.save(cadUser);
            log.info(String.format(USUARIO_CRIADO, username));
            return true;
        }
        return false;
    }
    private boolean isUserAvailable(String username, String email) {
        if (cadastralRepository.existsByEmail(email) || cadastralRepository.existsByUsername(username)) {
            log.info(String.format(USUARIO_EXISTENTE_TABELA_CADASTRAL, username, email));
            return false;
        }
        if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username)) {
            log.info(String.format(USUARIO_EXISTENTE_TABELA_USUARIO, username, email));
            return false;
        }
        return true;
    }
}
