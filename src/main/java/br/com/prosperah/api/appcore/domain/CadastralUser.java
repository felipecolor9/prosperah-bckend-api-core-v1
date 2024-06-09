package br.com.prosperah.api.appcore.domain;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CadastralUser {

    //TODO DTO Validation
    private String username;

    private String password;

    private String fullName;

    private String email;
    public static CadastralUser toCadastralUser(CadastralUserPersistData persistData) {
        return CadastralUser.builder()
                .username(persistData.getUsername())
                .password(persistData.getPassword())
                .fullName(persistData.getFullName())
                .email(persistData.getEmail())
                .build();
    }
}
