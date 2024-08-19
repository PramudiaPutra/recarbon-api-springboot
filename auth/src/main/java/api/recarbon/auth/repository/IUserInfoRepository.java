package api.recarbon.auth.repository;

import api.recarbon.auth.dto.UserInfoDto;
import api.recarbon.auth.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query(value = "SELECT * from common.user where email = ?1", nativeQuery = true)
    Optional<UserInfo> findByEmail(String email);

    @Query(value = "SELECT * from common.user where username = ?1", nativeQuery = true)
    Optional<UserInfo> findByUsername(String username);

    @Override
    List<UserInfo> findAll();

}
