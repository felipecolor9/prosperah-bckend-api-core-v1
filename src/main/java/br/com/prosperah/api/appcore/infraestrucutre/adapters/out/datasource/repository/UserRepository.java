package br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.repository;

import br.com.prosperah.api.appcore.infraestrucutre.adapters.out.datasource.model.UserPersistData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserPersistData, Long> {

    @Query("SELECT COUNT(u) > 0 FROM UserPersistData u WHERE u.username = :username")
    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM UserPersistData u WHERE u.email = :email")
    boolean existsByEmail(String email);

}
