package wat.f.bridgedu.domain.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "milestones")
public class MilestoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String title;
    private String memo;
    private byte importance;
    private byte achievement;
    private Date goal;
    private Date created;
    private Date modified;
    private boolean enabled;
    @OneToMany(mappedBy = "milestoneId")
    private List<TaskEntity> tasks;
    @OneToMany(mappedBy = "milestoneId")
    private List<CommentEntity> comments;
}
