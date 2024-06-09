package br.com.prosperah.api.appcore.infraestrucutre.controller;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.domain.LoginUserForm;
import br.com.prosperah.api.appcore.domain.User;
import br.com.prosperah.api.appcore.domain.Wallet;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.exceptions.UserNotFoundException;
import br.com.prosperah.api.appcore.infraestrucutre.InfraUserService;
import io.swagger.annotations.Api;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/psph/api/v1")
@Api(tags = "Users")
public class UserController {

    //TODO Exceptions para headers,body,params inválidos
    @Autowired
    InfraUserService infraUserService;

    @PostMapping("users/register")
    @ResponseStatus(CREATED)
    public ResponseEntity<CadastralUser> createUser(@RequestBody CadastralUser user) throws BadRequestException {
        //TODO implementar retorno de headers para autenticação

        return infraUserService.createCadastralUser(user);
    }

    @PutMapping("users/{userId}/authenticate")
    @ResponseStatus(CREATED)
    public ResponseEntity<User> createUser(@RequestHeader("Auth-Code") String authCode,
                                           @RequestHeader("Session-Hash") String sessionId,
                                           @RequestHeader("User-Email") String userEmail,
                                           @PathVariable("userId") String clientId) throws BadRequestException, UserNotFoundException {

        return infraUserService.validateCadastralUser(clientId, authCode, sessionId, userEmail);
    }

    @GetMapping("users/login")
    @ResponseStatus(OK)
    public ResponseEntity<Wallet> loginUser(@RequestBody LoginUserForm loginForm) throws UserNotFoundException {
        return infraUserService.loginAndAuthorize(loginForm);
    }
}
