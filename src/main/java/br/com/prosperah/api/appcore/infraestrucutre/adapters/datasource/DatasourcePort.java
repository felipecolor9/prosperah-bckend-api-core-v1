package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource;

import br.com.prosperah.api.appcore.domain.LoginUserForm;
import br.com.prosperah.api.appcore.exceptions.UserNotFoundException;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.UserPersistData;
import org.apache.coyote.BadRequestException;

import java.util.Optional;

public interface DatasourcePort {

    Optional<CadastralUserPersistData> saveCadastralUser(CadastralUserPersistData cadUser) throws BadRequestException;
    Optional<UserPersistData> saveConsolidatedUser(String clientId, String authCode, String sessionId, String userEmail) throws BadRequestException, UserNotFoundException;

    Optional<UserPersistData> findAndLogUser(LoginUserForm form);

}
