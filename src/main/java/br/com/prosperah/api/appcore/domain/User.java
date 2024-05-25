package br.com.prosperah.api.appcore.domain;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.UserPersistData;
import br.com.prosperah.api.appcore.utils.ConvertUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class User {

    private UUID id;

    private CadastralUser user;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private LocalDateTime creationDate;

    private LocalDateTime birthDate;

    private LocalDateTime validationDate;

    public static User toUser(UserPersistData persistData) {
        return User.builder()
                .id(ConvertUtils.convertBytesToUUID(persistData.getId()))
                .username(persistData.getUsername())
                .password(persistData.getPassword())
                .fullName(persistData.getFullName())
                .email(persistData.getEmail())
                .creationDate(persistData.getCreationDate().toLocalDateTime())
                .birthDate(persistData.getBirthDate().toLocalDateTime())
                .validationDate(persistData.getValidationDate().toLocalDateTime())
                .build();
    }
}
