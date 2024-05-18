package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.repository;

import br.com.prosperah.api.appcore.domain.CadastralUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastralUserRepository extends JpaRepository<CadastralUser, Long> {
}
