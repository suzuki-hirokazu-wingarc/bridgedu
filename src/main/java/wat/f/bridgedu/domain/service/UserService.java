package wat.f.bridgedu.domain.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.entity.AuthorityEntity;
import wat.f.bridgedu.domain.entity.UserEntity;
import wat.f.bridgedu.domain.repository.AuthorityRepository;
import wat.f.bridgedu.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public List<UserEntity> findByAuthority(String authority) {
        return userRepository.findByAuthorities_Authority(authority);
    }

    @Transactional
    public void create(
        String username,
        String displayName,
        String password,
        boolean enabled
    ) {
        UserEntity user = new UserEntity(username, displayName, password, enabled, Collections.emptyList());
        AuthorityEntity authority = new AuthorityEntity(username, "ROLE_STUDENT");
        userRepository.save(user);
        authorityRepository.save(authority);
    }

}
