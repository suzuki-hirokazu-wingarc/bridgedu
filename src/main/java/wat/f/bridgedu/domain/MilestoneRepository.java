package wat.f.bridgedu.domain;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MilestoneRepository {
    @Select("select * from milestones")
    List<MilestoneEntity> findAll();

    @Select("select * from milestones where id=#{id}")
    MilestoneEntity find(@Param("id") long id);

    @Insert("insert into milestones(header, description) values (#{header}, #{description})")
    void insert(@Param("header") String header, @Param("description") String description);
}
