package wat.f.bridgedu.domain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void create(
        String username,
        String displayName,
        String password,
        boolean enabled
    ) {
        userRepository.create(username, displayName, password, enabled);
        authorityRepository.create(username, "ROLE_STUDENT");
    }

}
