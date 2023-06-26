package wat.f.bridgedu.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "users")
public class UserEntity {
    @Id
    private String name;
    private String displayName;
    private String password;
    private boolean enabled;
    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
    private List<AuthorityEntity> authorities;
}
