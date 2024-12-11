package api.recarbon.auth.repository;

import api.recarbon.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = "select * from common.user where email  = ?1 or username = ?1", nativeQuery = true)
    Optional<User> getUserByEmailOrUsername(String username);
}
