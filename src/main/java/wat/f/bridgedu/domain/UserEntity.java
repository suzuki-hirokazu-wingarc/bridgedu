package wat.f.bridgedu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserEntity {
    private String name;
    private String display_name;
    private String password;
    private boolean enabled;
}
