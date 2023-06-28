package wat.f.bridgedu.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wat.f.bridgedu.domain.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
