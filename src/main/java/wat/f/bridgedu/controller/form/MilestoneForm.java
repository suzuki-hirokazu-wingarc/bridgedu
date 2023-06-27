package wat.f.bridgedu.controller.form;

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
    private byte importance;
    private byte achievement;
    private Date goal;
}
