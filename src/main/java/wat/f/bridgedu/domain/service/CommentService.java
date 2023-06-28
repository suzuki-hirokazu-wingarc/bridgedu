package wat.f.bridgedu.domain.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.entity.CommentEntity;
import wat.f.bridgedu.domain.entity.UserEntity;
import wat.f.bridgedu.domain.repository.CommentRepository;
import wat.f.bridgedu.domain.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(long milestoneId, String username, String body) {
        UserEntity user = userRepository.findById(username).get();
        commentRepository.save(new CommentEntity(
            0, milestoneId, body, Date.valueOf(LocalDate.now()), true, user
        ));
    }

    @Transactional
    public void update(long commentId, boolean enabled) {
        CommentEntity comment = commentRepository.findById(commentId).get();
        comment.setEnabled(enabled);
        commentRepository.save(comment);
    }
}