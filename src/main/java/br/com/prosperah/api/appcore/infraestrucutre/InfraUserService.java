package br.com.prosperah.api.appcore.infraestrucutre;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.domain.User;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.DatasourcePort;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.mail.EmailAuthenticationService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData.toPersistData;

@Service
public class InfraUserService {

    @Autowired
    DatasourcePort datasource;

    @Autowired
    EmailAuthenticationService emailService;

    @Transactional
    public ResponseEntity<CadastralUser> createCadastralUser(CadastralUser user) throws BadRequestException {
        var persistedCadUser = datasource.saveCadastralUser(toPersistData(user));

        persistedCadUser.ifPresent(cadastralUserPersistData -> emailService.sendAuthenticationEmail(cadastralUserPersistData.getEmail(),
                String.valueOf(cadastralUserPersistData.getCodAuth())));

        //TODO lançar badRequest com campo de body de requisicao vazio
//        {
//            "timestamp": "2024-05-26T20:19:01.323+00:00",
//                "status": 400,
//                "error": "Bad Request",
//                "trace": "org.springframework.http.converter.HttpMessageNotReadableException: Required request body is missing:

        //TODO lançar badRequest com corpo do body vazio {}

    //        "timestamp": "2024-05-26T20:20:12.248+00:00",
    //                "status": 500,
    //                "error": "Internal Server Error",
    //                "trace": "java.lang.NullPointerException\r\n\tat java.sql/java.sql.Timestamp.valueOf(Timestamp.java:498)\r\n\tat br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData.toPersistData
    //
        return new ResponseEntity<>(user, "Usuário criado com sucesso", 201);
    }

    public ResponseEntity<User> validateCadastralUser(String clientId, String authCode,String sessionId, String userEmail) throws BadRequestException {
        var persistedUser = datasource.saveUser(clientId, authCode, sessionId, userEmail);
        return persistedUser.map(userPersistData -> new ResponseEntity<>(User.toUser(userPersistData), "Usuário validado com sucesso", 201)).orElse(null);
    }

}
