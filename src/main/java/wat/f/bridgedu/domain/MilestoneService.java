package wat.f.bridgedu.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

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
    
}
