package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.UserPersistData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserPersistData, Long> {

    @org.springframework.data.jdbc.repository.query.Query("SELECT COUNT(u) FROM UserPersistData u WHERE u.username = :username")
    boolean existsByUsername(String username);

    @org.springframework.data.jdbc.repository.query.Query("SELECT COUNT(u) FROM UserPersistData u WHERE u.email = :email")
    boolean existsByEmail(String email);

}
