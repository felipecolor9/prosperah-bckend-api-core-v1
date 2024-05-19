package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model;

import br.com.prosperah.api.appcore.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

import static br.com.prosperah.api.appcore.utils.ConvertUtils.convertUUIDToBytes;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb001_usuario_cadastral")
public class UserPersistData {

    @Id
    private byte[] codUsr;

    private byte[] codUsrCadFk;

    private String usrNomeLogin;

    private String usrSenha;

    private String usrNomeCompleto;

    private String usrEmail;

    private Timestamp usrDataNasc;

    private Timestamp usrDataCriacao;

    private Timestamp usrDataValidacao;



    public UserPersistData toPersistData(User user) {
        return UserPersistData.builder()
                .codUsr(convertUUIDToBytes(user.getId()))
                .codUsrCadFk(convertUUIDToBytes(user.getUser().getId()))
                .usrNomeLogin(user.getUsername())
                .usrSenha(user.getPassword())
                .usrNomeCompleto(user.getFullName())
                .usrEmail(user.getEmail())
                .usrDataNasc(Timestamp.valueOf(user.getBirthDate()))
                .usrDataCriacao(Timestamp.valueOf(user.getCreationDate()))
                .usrDataValidacao(Timestamp.valueOf(user.getValidationDate()))
                .build();
    }
}
