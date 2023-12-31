package wat.f.bridgedu.domain.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.entity.MilestoneEntity;
import wat.f.bridgedu.domain.repository.MilestoneRepository;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;
    
    public List<MilestoneEntity> findByUser(String username) {
        return milestoneRepository.findByUsername(username);
        // return milestoneRepository.findOfUser(username);
    }
    
    public MilestoneEntity find(long id) {
        return milestoneRepository.findById(id).get();
    }

    @Transactional
    public void create(MilestoneEntity milestone) {
        milestoneRepository.save(milestone);
    }

    @Transactional
    public void create(String username, String title, String memo, byte importance, byte achievement, Date goal) {
        milestoneRepository.save(new MilestoneEntity(
            0, username, title, memo, importance, achievement, goal,
            Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), true, Collections.emptyList(), Collections.emptyList()
        ));
    }

    @Transactional
    public void update(long id, String username, String title, String memo, byte importance, byte achievement, Date goal) {
        MilestoneEntity milestone = milestoneRepository.findById(id).get();
        milestoneRepository.save(new MilestoneEntity(
            id, username, title, memo, importance, achievement, goal,
            milestone.getCreated(), Date.valueOf(LocalDate.now()), true, milestone.getTasks(), Collections.emptyList()
        ));
    }

    @Transactional
    public void disable(long id) {
        MilestoneEntity milestone = milestoneRepository.findById(id).get();
        milestone.setEnabled(false);
        milestone.getComments().forEach(c -> c.setEnabled(false));
        milestone.getTasks().forEach(t -> t.setEnabled(false));
        milestoneRepository.save(milestone);
    }
    
}
