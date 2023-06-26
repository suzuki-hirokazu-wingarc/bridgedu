package wat.f.bridgedu.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wat.f.bridgedu.domain.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // @Select("select * from users")
    // List<UserEntity> findAll();

    // @Select("select * from users where name=#{name}")
    // UserEntity find(@Param("name") String name);

    // @Insert("insert into users(name, display_name, password, enabled) values (#{name}, #{displayName}, #{password}, #{enabled})")
    // void create(@Param("name") String name, @Param("displayName") String displayName, @Param("password") String password, @Param("enabled") boolean enabled);
}
