package wat.f.bridgedu.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.entity.TagEntity;
import wat.f.bridgedu.domain.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<TagEntity> findAll() {
        return tagRepository.findAll();
    }

    public TagEntity find(long id){
        return tagRepository.findById(id).get();
    }
}
