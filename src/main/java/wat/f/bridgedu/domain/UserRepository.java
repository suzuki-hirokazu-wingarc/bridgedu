package wat.f.bridgedu.domain;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
    @Select("select * from users")
    List<UserEntity> findAll();

    @Select("select * from users where name=#{name}")
    UserEntity find(@Param("name") String name);

    @Insert("insert into users(name, display_name, password, enabled) values (#{name}, #{displayName}, #{password}, #{enabled})")
    void create(@Param("name") String name, @Param("displayName") String displayName, @Param("password") String password, @Param("enabled") boolean enabled);
}
