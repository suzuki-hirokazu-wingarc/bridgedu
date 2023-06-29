package wat.f.bridgedu.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.controller.form.CommentForm;
import wat.f.bridgedu.controller.form.MilestoneForm;
import wat.f.bridgedu.domain.entity.UserDetailsImpl;
import wat.f.bridgedu.domain.service.CommentService;
import wat.f.bridgedu.domain.service.MilestoneService;
import wat.f.bridgedu.domain.service.TagService;
import wat.f.bridgedu.domain.service.UserService;

@RequiredArgsConstructor
@Controller
public class MilestoneController {
    private final MilestoneService milestoneService;// = new MilestoneService();
    private final CommentService commentService;
    private final TagService tagService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String forbidden(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("loginUser", userService.find(user.getUsername()));
        return "forbidden";
    }
    
    @GetMapping("{username}")
    public String showList(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        model.addAttribute("loginUser", userService.find(user.getUsername()));
        model.addAttribute("username", username);
        model.addAttribute("milestoneList", milestoneService.findByUser(username));
        model.addAttribute("tagList", tagService.findAll());
        return "milestones/list";
    }
    
    @PostMapping("{username}/creation")
    public String create(
        @AuthenticationPrincipal UserDetailsImpl user,
        @Validated MilestoneForm form,
        @PathVariable("username") String username,
        BindingResult bindingResult, Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        if (bindingResult.hasErrors()) {
            return showCreationForm(user, form, username, model);
        }
        model.addAttribute("loginUser", userService.find(user.getUsername()));

        try {
            milestoneService.create(
                username,
                form.getTitle(),
                form.getMemo(),
                form.getImportance(),
                form.getAchievement(),
                form.getGoal()
            );
        } catch (NoSuchElementException e) {
            return showCreationForm(user, form, username, model);
        }
        return String.format("redirect:/%s", username);
    }

    @GetMapping("{username}/creation")
    public String showCreationForm(
        @AuthenticationPrincipal UserDetailsImpl user,
        @ModelAttribute MilestoneForm form,
        @PathVariable("username") String username,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        model.addAttribute("loginUser", userService.find(user.getUsername()));
        model.addAttribute("username", username);
        return "milestones/create";
    }
    
    @GetMapping("{username}/{milestoneId}")
    public String showDetail(
        @AuthenticationPrincipal UserDetailsImpl user,
        @ModelAttribute CommentForm commentForm,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        model.addAttribute("loginUser", userService.find(user.getUsername()));
        model.addAttribute("username", username);
        model.addAttribute("milestoneId", milestoneId);
        model.addAttribute("milestone", milestoneService.find(milestoneId));
        return "milestones/detail";
    }

    @GetMapping("{username}/{milestoneId}/edit")
    public String showEdit(
        @AuthenticationPrincipal UserDetailsImpl user,
        @ModelAttribute MilestoneForm form,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        var milestone = milestoneService.find(milestoneId);
        
        form.setTitle(milestone.getTitle());
        form.setMemo(milestone.getMemo());
        form.setImportance(milestone.getImportance());
        form.setAchievement(milestone.getAchievement());
        form.setGoal(milestone.getGoal());
        model.addAttribute("loginUser", userService.find(user.getUsername()));
        model.addAttribute("username", username);
        model.addAttribute("milestoneId", milestoneId);
        return "milestones/edit";
    }

    @PostMapping("{username}/{milestoneId}/edit")
    public String edit(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        @Validated MilestoneForm form,
        BindingResult bindingResult, Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        if (bindingResult.hasErrors()) {
            return showEdit(user, form, username, milestoneId, model);
        }

        milestoneService.update(
            milestoneId,
            username,
            form.getTitle(),
            form.getMemo(),
            form.getImportance(),
            form.getAchievement(),
            form.getGoal()
        );
        return String.format("redirect:/%s/%s", username, milestoneId);
    }

    @PostMapping("{username}/{milestoneId}/deletion")
    public String disable(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";

        milestoneService.disable(milestoneId);
        return String.format("redirect:/%s", username);
    }
    
    // @GetMapping("{username}/dump")
    // @ResponseBody
    // public String dump(
    //     @ModelAttribute MilestoneForm form,
    //     @PathVariable("username") String username,
    //     Model model
    // ) {
    //     return milestoneService.findByUser(username).toString();
    // }
    
    @PostMapping("{username}/{milestoneId}/comment/post")
    public String postComment(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        @Validated CommentForm form,
        BindingResult bindingResult, Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        if (bindingResult.hasErrors()) {
            return showDetail(user, form, user.getUsername(), milestoneId, model);
        }

        commentService.create(
            milestoneId,
            user.getUsername(),
            form.getBody()
        );
        return String.format("redirect:/%s/%d", username, milestoneId);
    }
    
    @PostMapping("{username}/{milestoneId}/comment/{commentId}/deletion")
    public String deleteComment(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        @PathVariable("commentId") long commentId,
        CommentForm form,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        try {
            commentService.update(commentId, false);
        } catch (NoSuchElementException e) {
            return showDetail(user, form, username, milestoneId, model);
        }
        
        return String.format("redirect:/%s/%d", username, milestoneId);
    }
}
