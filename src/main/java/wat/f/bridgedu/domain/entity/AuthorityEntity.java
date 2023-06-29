package wat.f.bridgedu.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wat.f.bridgedu.controller.UserRole;

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
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
