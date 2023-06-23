package wat.f.bridgedu.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
    @Select("select * from users where name=#{name}")
    UserEntity find(@Param("name") String name);

    @Select("select * from authorities where username=#{username}")
    List<AuthorityEntity> findAuthorities(@Param("username") String username);
}
