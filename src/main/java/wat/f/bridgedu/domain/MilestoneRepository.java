package wat.f.bridgedu.domain;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MilestoneRepository {
    @Select("select * from milestones")
    List<MilestoneEntity> findAll();

    @Select("select * from milestones where username=#{username}")
    List<MilestoneEntity> findOfUser(@Param("username") String username);

    @Select("select * from milestones where id=#{id}")
    MilestoneEntity find(@Param("id") long id);

    @Insert("insert into milestones(username, title, memo, importance, achievement, goal, created, modified) values (#{username}, #{title}, #{memo}, #{importance}, #{achievement}, #{goal}, #{created}, #{modified})")
    void insert(
        @Param("username") String username,
        @Param("title") String title,
        @Param("memo") String memo,
        @Param("importance") byte importance,
        @Param("achievement") byte achievement,
        @Param("goal") Date goal,
        @Param("created") Date created,
        @Param("modified") Date modified
    );
}
