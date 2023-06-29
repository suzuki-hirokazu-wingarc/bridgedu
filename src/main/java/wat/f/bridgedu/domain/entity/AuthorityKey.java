package wat.f.bridgedu.domain.entity;

import java.io.Serializable;

import lombok.Data;
import wat.f.bridgedu.controller.UserRole;

@Data
@SuppressWarnings("serial")
public class AuthorityKey implements Serializable{
    private String username;
    private UserRole role;
}
