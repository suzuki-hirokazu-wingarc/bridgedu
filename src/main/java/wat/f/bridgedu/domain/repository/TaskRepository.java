package wat.f.bridgedu.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wat.f.bridgedu.domain.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    public List<TaskEntity> findByEnabled(boolean enabled);
}
