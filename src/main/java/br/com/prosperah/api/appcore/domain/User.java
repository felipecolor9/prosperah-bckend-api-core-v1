package br.com.prosperah.api.appcore.domain;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.UserPersistData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class User {

    //TODO DTO Validation

    private String username;

    private String password;

    private String fullName;

    private String email;


    private LocalDateTime birthDate;

    private LocalDateTime validationDate;

    public static User toUser(UserPersistData persistData) {
        return User.builder()
                .username(persistData.getUsername())
                .password(persistData.getPassword())
                .fullName(persistData.getFullName())
                .email(persistData.getEmail())
//                .birthDate(persistData.getBirthDate().toLocalDateTime())
                .validationDate(persistData.getValidationDate().toLocalDateTime())
                .build();
    }
}
