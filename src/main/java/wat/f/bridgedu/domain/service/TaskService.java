package wat.f.bridgedu.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.entity.TaskEntity;
import wat.f.bridgedu.domain.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    public void create(TaskEntity task) {
        taskRepository.save(task);
    }
}
