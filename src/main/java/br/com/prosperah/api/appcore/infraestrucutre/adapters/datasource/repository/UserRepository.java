package br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.repository;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.datasource.model.UserPersistData;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserPersistData, Long> {

    @Query("SELECT COUNT(u) FROM UserPersistData u WHERE u.username = :username")
    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) FROM UserPersistData u WHERE u.email = :email")
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) FROM UserPersistData u WHERE u.email = :email")
    Optional<UserPersistData> findByUsernameAndPassword(String username, String password);

}
