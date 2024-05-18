package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//Editar
@AllArgsConstructor
@Builder
@Table(name = "${spring.datasource.schema-name}.cadastral_user_data")
public class CadastralUserPersistData {


}
