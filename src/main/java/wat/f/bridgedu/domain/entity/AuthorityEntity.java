package wat.f.bridgedu.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "authorities")
@IdClass(AuthorityKey.class)
public class AuthorityEntity {
    @Id
    private String username;
    @Id
    private String authority;
}
