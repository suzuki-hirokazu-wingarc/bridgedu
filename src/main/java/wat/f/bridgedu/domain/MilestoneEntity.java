package wat.f.bridgedu.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MilestoneEntity {
    private long id;
    private String username;
    private String title;
    private String memo;
    private byte importance;
    private byte archivement;
    private Date goal;
    private Date create;
    private Date modified;
}
