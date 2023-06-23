package wat.f.bridgedu.domain;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthorityRepository {

    @Select("select * from authorities where username=#{username}")
    List<AuthorityEntity> findAuthorities(@Param("username") String username);

    @Insert("insert into authorities(username, authority) values (#{username}, #{authority})")
    void create(@Param("username") String username, @Param("authority") String authority);

}
