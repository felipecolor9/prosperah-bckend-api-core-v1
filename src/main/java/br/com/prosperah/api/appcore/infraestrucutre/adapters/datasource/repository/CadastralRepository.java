package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.CadastralUserPersistData;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadastralRepository extends JpaRepository<CadastralUserPersistData, Long> {

    @Query("SELECT COUNT(u) FROM CadastralUserPersistData u WHERE u.username = :username")
    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) FROM CadastralUserPersistData u WHERE u.email = :email")
    boolean existsByEmail(String email);

    Optional<CadastralUserPersistData> findById(byte[] clientId);
}
