package wat.f.bridgedu.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.service.CommentService;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;
}
