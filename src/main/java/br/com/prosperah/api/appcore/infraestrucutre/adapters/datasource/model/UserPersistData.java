package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model;

import br.com.prosperah.api.appcore.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

import static br.com.prosperah.api.appcore.utils.ConvertUtils.convertUUIDToBytes;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb002_usuario_consolidado")
public class UserPersistData {

    @Id
    @Column(name = "cod_usr")
    private byte[] id;

    @OneToOne
    @JoinColumn(name = "cod_usr_cad_fk")
    private CadastralUserPersistData cadastralUser;

    @Column(name = "usr_nome_login")
    private String username;

    @Column(name = "usr_senha")
    private String password;

    @Column(name = "usr_nome_completo")
    private String fullName;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_data_nasc")
    private Timestamp birthDate;

    @Column(name = "usr_data_criacao")
    private Timestamp creationDate;

    @Column(name = "usr_data_validacao")
    private Timestamp validationDate;

    public UserPersistData toPersistData(User user) {
        return UserPersistData.builder()
                .id(convertUUIDToBytes(user.getId()))
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .birthDate(Timestamp.valueOf(user.getBirthDate()))
                .creationDate(Timestamp.valueOf(user.getCreationDate()))
                .validationDate(Timestamp.valueOf(user.getValidationDate()))
                .build();
    }
}
