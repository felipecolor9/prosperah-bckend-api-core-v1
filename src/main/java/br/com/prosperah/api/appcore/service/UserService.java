package br.com.prosperah.api.appcore.service;

import br.com.prosperah.api.appcore.domain.User;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.DatasourcePort;
import br.com.prosperah.api.appcore.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private DatasourcePort datasource;
    public ResponseEntity<User> createUser(User user) {
        if (isUsernameAvailable(user.getUsername())) {
            return new ResponseEntity<>(user, "Usuário criado com sucesso", 200);
        }
        return new ResponseEntity<>(user, "Usuário criado com sucesso", 200);
    }

    private boolean isUsernameAvailable(String username) {
        return true;
    }
}
