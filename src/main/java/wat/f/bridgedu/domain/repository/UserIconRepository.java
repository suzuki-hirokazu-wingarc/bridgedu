package wat.f.bridgedu.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wat.f.bridgedu.domain.entity.UserIconEntity;

@Repository
public interface UserIconRepository extends JpaRepository<UserIconEntity, Long> {
}
