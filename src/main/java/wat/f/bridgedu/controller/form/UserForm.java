package wat.f.bridgedu.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserForm {
    @NotBlank
    @Size(max = 64)
    private String name;
    @NotBlank
    @Size(max = 64)
    private String displayName;
    @NotBlank
    @Size(max = 64)
    private String password;
    private MultipartFile icon;
}
