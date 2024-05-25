package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.repository;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.CadastralUserPersistData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CadastralRepository extends JpaRepository<CadastralUserPersistData, Long> {

    @org.springframework.data.jdbc.repository.query.Query("SELECT COUNT(u) FROM CadastralUserPersistData u WHERE u.username = :username")
    boolean existsByUsername(String username);

    @org.springframework.data.jdbc.repository.query.Query("SELECT COUNT(u) FROM CadastralUserPersistData u WHERE u.email = :email")
    boolean existsByEmail(String email);
}
