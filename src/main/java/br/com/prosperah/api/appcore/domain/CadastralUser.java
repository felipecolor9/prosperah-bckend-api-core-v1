package br.com.prosperah.api.appcore.domain;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.CadastralUserPersistData;
import br.com.prosperah.api.appcore.utils.ConvertUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class CadastralUser {

    private UUID id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private LocalDateTime creationDate;

    public static CadastralUser toCadastralUser(CadastralUserPersistData persistData) {
        return CadastralUser.builder()
                .id(ConvertUtils.convertBytesToUUID(persistData.getId()))
                .username(persistData.getUsername())
                .password(persistData.getPassword())
                .fullName(persistData.getFullName())
                .email(persistData.getEmail())
                .creationDate(persistData.getCreationDate().toLocalDateTime())
                .build();
    }
}
