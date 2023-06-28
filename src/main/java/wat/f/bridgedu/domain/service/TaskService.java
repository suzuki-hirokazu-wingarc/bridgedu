package wat.f.bridgedu.domain.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.entity.MilestoneEntity;
import wat.f.bridgedu.domain.entity.TaskEntity;
import wat.f.bridgedu.domain.repository.MilestoneRepository;
import wat.f.bridgedu.domain.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final MilestoneRepository milestoneRepository;

    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public void create(TaskEntity task) {
        MilestoneEntity milestone = milestoneRepository.findById(task.getMilestoneId()).get();
        taskRepository.save(task);
        milestone.setModified(Date.valueOf(LocalDate.now()));
        milestoneRepository.save(milestone);
    }
}
