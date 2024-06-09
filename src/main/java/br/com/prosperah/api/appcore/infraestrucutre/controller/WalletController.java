package br.com.prosperah.api.appcore.infraestrucutre.controller;

import br.com.prosperah.api.appcore.domain.Wallet;
import br.com.prosperah.api.appcore.domain.WalletApply;
import br.com.prosperah.api.appcore.domain.response.ResponseEntity;
import br.com.prosperah.api.appcore.infraestrucutre.InfraWalletService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/psph/api/v1")
@Api(tags = "Wallet")
public class WalletController {

    @Autowired
    InfraWalletService infraWalletService;

    @PutMapping("/users/{userId}/wallet/modify")
    public ResponseEntity<Wallet> updateWallet(@PathVariable("userId") String userId, @RequestBody WalletApply application) {

        return null;
    }
}
