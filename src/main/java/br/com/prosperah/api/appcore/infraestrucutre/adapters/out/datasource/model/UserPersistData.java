package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "${spring.datasource.schema-name.table-user.name}")
public class UserPersistData {

    @Id
    private Long id;
}
