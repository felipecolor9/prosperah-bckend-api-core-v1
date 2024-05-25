package br.com.prosperah.api.appcore.infraestrucutre.controller;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.infraestrucutre.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/psph/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CadastralUser> createUser(@RequestBody CadastralUser user) throws BadRequestException {
        return userService.createCadastralUser(user);
    }

}
