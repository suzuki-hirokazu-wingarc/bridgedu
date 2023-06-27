package wat.f.bridgedu.domain.service;

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
    
}
