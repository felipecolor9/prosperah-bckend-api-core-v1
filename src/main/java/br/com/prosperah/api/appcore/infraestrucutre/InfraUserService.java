package br.com.prosperah.api.appcore.infraestrucutre;

import br.com.prosperah.api.appcore.constants.Constants;
import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.domain.LoginUserForm;
import br.com.prosperah.api.appcore.domain.User;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.exceptions.EmptyRequestBodyException;
import br.com.prosperah.api.appcore.exceptions.UserNotFoundException;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.UserDatasourceService;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.WalletDatasourceService;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.mail.EmailAuthenticationService;
import br.com.prosperah.api.appcore.utils.ConvertUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.com.prosperah.api.appcore.domain.User.toUser;
import static br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData.toPersistData;

@Service
public class InfraUserService {

    @Autowired
    UserDatasourceService userDatasource;

    @Autowired
    WalletDatasourceService walletDatasource;

    @Autowired
    EmailAuthenticationService emailService;


    @Transactional
    public ResponseEntity<CadastralUser> createCadastralUser(CadastralUser user) throws BadRequestException {

        if (user == null) throw new EmptyRequestBodyException(Constants.REQUISICAO_BODY_VAZIO);
        var persistedCadUser = userDatasource.saveCadastralUser(toPersistData(user));

        persistedCadUser.ifPresent(cadastralUserPersistData -> emailService.sendAuthenticationEmail(cadastralUserPersistData.getEmail(),
                String.valueOf(cadastralUserPersistData.getCodAuth())));

        return new ResponseEntity<>(user, "Usuário criado com sucesso", 201);
    }

    @Transactional
    public ResponseEntity<User> validateCadastralUser(String clientId, String authCode,String sessionId, String userEmail) throws BadRequestException, UserNotFoundException {
        var persistedUser = userDatasource.saveConsolidatedUser(clientId, authCode, sessionId, userEmail).orElseThrow(BadRequestException::new);

        walletDatasource.createWallet(persistedUser.getId());

        return new ResponseEntity<>(toUser(persistedUser), "Usuário validado com sucesso", 201);
    }
    public ResponseEntity<User> loginAndAuthorize(LoginUserForm form) throws UserNotFoundException {
        var foundUser = userDatasource.findAndLogUser(form);
        if (foundUser.isPresent()) return new ResponseEntity<>(toUser(foundUser.get()), "Usuário encontrado com sucesso", 200);
        else throw new UserNotFoundException();
    }


}
