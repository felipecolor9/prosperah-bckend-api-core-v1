package br.com.prosperah.api.appcore.infraestrucutre.controller;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.infraestrucutre.InfraUserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/psph/api/v1/users")
public class UserController {

    @Autowired
    InfraUserService infraUserService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CadastralUser> createUser(@RequestBody CadastralUser user) throws BadRequestException {
        return infraUserService.createCadastralUser(user);
    }

}
