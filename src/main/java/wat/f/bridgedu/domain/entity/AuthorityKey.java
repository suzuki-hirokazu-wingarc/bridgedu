package wat.f.bridgedu.domain.entity;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class AuthorityKey implements Serializable{
    private String username;
    private String authority;
}
