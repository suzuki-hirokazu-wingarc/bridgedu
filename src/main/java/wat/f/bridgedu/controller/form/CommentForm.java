package wat.f.bridgedu.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentForm {
    @NotEmpty
    @Size(max = 255)
    private String body;
}
