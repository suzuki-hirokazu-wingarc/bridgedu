package wat.f.bridgedu.domain;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MilestoneForm {
    @NotBlank
    @Size(max = 256)
    private String title;
    @NotBlank
    @Size(max = 256)
    private String memo;
    // @NotBlank
    private byte importance;
    // @NotBlank
    private byte archivement;
    // @NotBlank
    private Date goal;
}
