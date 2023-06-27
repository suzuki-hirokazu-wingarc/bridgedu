package wat.f.bridgedu.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TaskForm {
    @NotBlank
    @Size(max = 255)
    private String name;
    private byte importance;
    private byte achievement;
}
