package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.repository;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.CadastralUserPersistData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastralUserRepository extends JpaRepository<CadastralUserPersistData, Long> {
}
