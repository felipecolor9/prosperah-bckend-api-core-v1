package br.com.prosperah.api.appcore.controller;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.domain.User;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.DatasourcePort;
import br.com.prosperah.api.appcore.response.ResponseEntity;
import br.com.prosperah.api.appcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psph/api/v1/users/")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public ResponseEntity<CadastralUser> createUser(@RequestBody CadastralUser user) {
        return userService.createCadastralUser(user);
    }

}
