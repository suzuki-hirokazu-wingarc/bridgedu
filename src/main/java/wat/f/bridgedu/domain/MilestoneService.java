package wat.f.bridgedu.domain;

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
    public void create(String milestone, String description) {
        milestoneRepository.insert(milestone, description);
    }
}
