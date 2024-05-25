package br.com.prosperah.api.appcore.infraestrucutre.service;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.DatasourcePort;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData.toPersistData;

@Service
public class UserService {

    @Autowired
    private DatasourcePort datasource;

    @Transactional
    public ResponseEntity<CadastralUser> createCadastralUser(CadastralUser user) throws BadRequestException {
        if (datasource.saveCadastralUser(toPersistData(user))) {
            sendAuthenticationEmail(user.getEmail());
        }
        return new ResponseEntity<>(user, "Usuário criado com sucesso", 201);
    }

    private void sendAuthenticationEmail(String emailAddress) {


    }
}
