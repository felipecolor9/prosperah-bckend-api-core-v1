package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Editar
@AllArgsConstructor
@Builder
@Entity
@Table(name = "${spring.datasource.schema-name.table-cadastral-user.name}")
public class CadastralUserPersistData {

    @Id
    private Long id;
}
