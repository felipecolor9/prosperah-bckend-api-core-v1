package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

import static br.com.prosperah.api.appcore.utils.ConvertUtils.convertUUIDToBytes;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb002_usuario_consolidado")
public class CadastralUserPersistData {

    @Id
    private byte[] codUsr;

    private String usrNameLogin;

    private String usrSenha;

    private String usrNomeCompleto;

    private String usrEmail;

    private Timestamp usrDataCriacao;

    public CadastralUserPersistData toPersistData(CadastralUser cadastralUser) {
        return CadastralUserPersistData.builder()
                .codUsr(convertUUIDToBytes(cadastralUser.getId()))
                .usrNameLogin(cadastralUser.getUsername())
                .usrSenha(cadastralUser.getPassword())
                .usrNomeCompleto(cadastralUser.getFullName())
                .usrEmail(cadastralUser.getEmail())
                .usrDataCriacao(Timestamp.valueOf(cadastralUser.getCreationDate()))
                .build();
    }
}
