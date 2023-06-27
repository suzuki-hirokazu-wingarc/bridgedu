package wat.f.bridgedu.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wat.f.bridgedu.domain.entity.AuthorityEntity;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {

    List<AuthorityEntity> findByUsername(String username);

    // @Select("select * from authorities where username=#{username}")
    // List<AuthorityEntity> findAuthorities(@Param("username") String username);

    // @Insert("insert into authorities(username, authority) values (#{username}, #{authority})")
    // void create(@Param("username") String username, @Param("authority") String authority);

}
