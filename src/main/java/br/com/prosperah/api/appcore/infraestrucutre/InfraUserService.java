package br.com.prosperah.api.appcore.infraestrucutre;

import br.com.prosperah.api.appcore.constants.Constants;
import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.domain.LoginUserForm;
import br.com.prosperah.api.appcore.domain.User;
import br.com.prosperah.api.appcore.exceptions.EmptyRequestBodyException;
import br.com.prosperah.api.appcore.exceptions.UserNotFoundException;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.DatasourcePort;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.mail.EmailAuthenticationService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.com.prosperah.api.appcore.domain.User.toUser;
import static br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData.toPersistData;

@Service
public class InfraUserService {

    @Autowired
    DatasourcePort datasource;

    @Autowired
    EmailAuthenticationService emailService;

    @Transactional
    public ResponseEntity<CadastralUser> createCadastralUser(CadastralUser user) throws BadRequestException {

        if (user == null) throw new EmptyRequestBodyException(Constants.REQUISICAO_BODY_VAZIO);
        var persistedCadUser = datasource.saveCadastralUser(toPersistData(user));

        persistedCadUser.ifPresent(cadastralUserPersistData -> emailService.sendAuthenticationEmail(cadastralUserPersistData.getEmail(),
                String.valueOf(cadastralUserPersistData.getCodAuth())));

        return new ResponseEntity<>(user, "Usuário criado com sucesso", 201);
    }

    @Transactional
    public ResponseEntity<User> validateCadastralUser(String clientId, String authCode,String sessionId, String userEmail) throws BadRequestException, UserNotFoundException {
        var persistedUser = datasource.saveConsolidatedUser(clientId, authCode, sessionId, userEmail);
        return persistedUser.map(userPersistData -> new ResponseEntity<>(toUser(userPersistData), "Usuário validado com sucesso", 201)).orElse(null);
    }
    public ResponseEntity<User> loginAndAuthorize(LoginUserForm form) throws UserNotFoundException {
        var foundUser = datasource.findAndLogUser(form);
        if (foundUser.isPresent()) return new ResponseEntity<>(toUser(foundUser.get()), "Usuário encontrado com sucesso", 200);
        else throw new UserNotFoundException();
    }


}
