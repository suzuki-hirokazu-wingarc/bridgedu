package wat.f.bridgedu.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.controller.UserRole;
import wat.f.bridgedu.domain.entity.AuthorityEntity;
import wat.f.bridgedu.domain.entity.UserEntity;
import wat.f.bridgedu.domain.entity.UserIconEntity;
import wat.f.bridgedu.domain.repository.AuthorityRepository;
import wat.f.bridgedu.domain.repository.UserIconRepository;
import wat.f.bridgedu.domain.repository.UserRepository;
import wat.f.bridgedu.domain.service.exception.AlreadyExistUsernameException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserIconRepository userIconRepository;

    @PostConstruct
    public void init() throws SQLException, IOException {
        Resource resource = new ClassPathResource("static/" + "defaultIcon.png");
        try (InputStream is = resource.getInputStream()) {
            Blob blob = new SerialBlob(is.readAllBytes());
            UserIconEntity userIcon = new UserIconEntity(1, blob);
            userIconRepository.save(userIcon);
        }
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public List<UserEntity> findByRole(UserRole role) {
        return userRepository.findByAuthorities_Role(role);
    }

    public UserEntity find(String username) {
        return userRepository.findById(username).get();
    }

    @Transactional
    public void create(
        String username,
        String displayName,
        String password,
        boolean enabled,
        Blob icon
    ) throws SQLException {
        if (userRepository.existsById(username)) {
            throw new AlreadyExistUsernameException(username);
        }
        UserIconEntity userIcon;
        if (icon.length() == 0) {
            userIcon = userIconRepository.findById(1L).get();
        } else {
            userIcon = new UserIconEntity(0, icon);
        }
        userIconRepository.save(userIcon);
        UserEntity user = new UserEntity(username, displayName, password, enabled, Collections.emptyList(), userIcon);
        AuthorityEntity authority = new AuthorityEntity(username, UserRole.ROLE_STUDENT);
        userRepository.save(user);
        authorityRepository.save(authority);
    }

}
