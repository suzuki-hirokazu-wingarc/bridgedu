package wat.f.bridgedu.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepository extends JpaRepository<MilestoneEntity, Long>{
    List<MilestoneEntity> findByUsername(String username);
    // @Select("select * from milestones")
    // List<MilestoneEntity> findAll();

    // @Select("select * from milestones where username=#{username}")
    // List<MilestoneEntity> findOfUser(@Param("username") String username);

    // @Select("select * from milestones where id=#{id}")
    // MilestoneEntity find(@Param("id") long id);

    // @Insert("insert into milestones(username, title, memo, importance, achievement, goal, created, modified) values (#{username}, #{title}, #{memo}, #{importance}, #{achievement}, #{goal}, #{created}, #{modified})")
    // void insert(
    //     @Param("username") String username,
    //     @Param("title") String title,
    //     @Param("memo") String memo,
    //     @Param("importance") byte importance,
    //     @Param("achievement") byte achievement,
    //     @Param("goal") Date goal,
    //     @Param("created") Date created,
    //     @Param("modified") Date modified
    // );
}
