package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model;

import br.com.prosperah.api.appcore.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb002_usuario_consolidado")
public class UserPersistData {

    @Id
    @Column(name = "cod_usr")
    private byte[] id;

    @Column(name = "cod_usr_cad_fk")
    private byte[] cadastralUser;

    @Column(name = "usr_nome_login")
    private String username;

    @Column(name = "usr_senha")
    private String password;

    @Column(name = "usr_nome_completo")
    private String fullName;

    @Column(name = "usr_email")
    private String email;

    //TODO Criar no BD
//    @Column(name = "usr_data_nasc")
//    private Timestamp birthDate;

    @Column(name = "usr_data_criacao")
    private Timestamp creationDate;

    @Column(name = "usr_data_validacao")
    private Timestamp validationDate;

    public static UserPersistData toPersistData(User user) {
        return UserPersistData.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .email(user.getEmail())
//                .birthDate(Timestamp.valueOf(user.getBirthDate()))
                .validationDate(Timestamp.valueOf(user.getValidationDate()))
                .build();
    }
}
