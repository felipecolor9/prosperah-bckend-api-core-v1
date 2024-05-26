package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

import static br.com.prosperah.api.appcore.utils.ConvertUtils.convertUUIDToBytes;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "tb001_usuario_cadastral")
public class CadastralUserPersistData {
    @Id
    @Column(name = "cod_usr")
    private byte[] id;

    @Column(name = "usr_nome_login")
    private String username;

    @Column(name = "usr_senha")
    private String password;

    @Column(name = "usr_nome_completo")
    private String fullName;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_data_criacao")
    private Timestamp creationDate;

    @Column(name = "cod_auth_email")
    private int codAuth;

    public static CadastralUserPersistData toPersistData(CadastralUser user) {
        return CadastralUserPersistData.builder()
                .id(convertUUIDToBytes(user.getId()))
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .creationDate(Timestamp.valueOf(user.getCreationDate()))
                .build();
    }
}
