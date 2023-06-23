package wat.f.bridgedu.domain;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneRepository milestoneRepository;

    public List<MilestoneEntity> findAll() {
        return milestoneRepository.findAll();
    }

    public MilestoneEntity find(long id) {
        return milestoneRepository.find(id);
    }

    @Transactional
    public void create(
        String username,
        String title,
        String memo,
        byte importance,
        byte achievement,
        Date goal,
        Date created,
        Date modified
    ) {
        milestoneRepository.insert(username, title, memo, importance, achievement, goal, created, modified);
    }
    
}
