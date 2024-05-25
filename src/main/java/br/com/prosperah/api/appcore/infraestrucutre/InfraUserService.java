package br.com.prosperah.api.appcore.infraestrucutre;

import br.com.prosperah.api.appcore.domain.CadastralUser;
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

        persistedCadUser.ifPresent(cadastralUserPersistData ->
                emailService.sendAuthenticationEmail(cadastralUserPersistData.getEmail(),
                String.valueOf(cadastralUserPersistData.getCodAuth())));

        return new ResponseEntity<>(user, "Usuário criado com sucesso", 201);
    }


}
