package br.com.prosperah.api.appcore.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginUserForm {

    private String username;

    private String password;
}
