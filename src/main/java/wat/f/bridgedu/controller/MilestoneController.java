package wat.f.bridgedu.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import wat.f.bridgedu.controller.form.MilestoneForm;
import wat.f.bridgedu.domain.entity.UserDetailsImpl;
import wat.f.bridgedu.domain.service.MilestoneService;

@AllArgsConstructor
@Controller
public class MilestoneController {
    private MilestoneService milestoneService;// = new MilestoneService();
    
    @GetMapping("{username}")
    public String showList(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        Model model
    ) {
        // if (!user.getUsername().equals(username)) {
            
        // }
        model.addAttribute("username", username);
        model.addAttribute("milestoneList", milestoneService.findByUser(username));
        return "milestones/list";
    }
    
    @PostMapping("{username}/creation")
    public String create(
        @AuthenticationPrincipal UserDetailsImpl user,
        @Validated MilestoneForm form,
        @PathVariable("username") String username,
        BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form, username, model);
        }

        milestoneService.create(
            username,
            form.getTitle(),
            form.getMemo(),
            form.getImportance(),
            form.getAchievement(),
            form.getGoal()
        );
        return String.format("redirect:/%s", username);
    }

    @GetMapping("{username}/creation")
    public String showCreationForm(
        @ModelAttribute MilestoneForm form,
        @PathVariable("username") String username,
        Model model
    ) {
        model.addAttribute("username", username);
        return "milestones/create";
    }
    
    @GetMapping("{username}/{milestoneId}")
    public String showDetail(
        @ModelAttribute MilestoneForm form,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        Model model
    ) {
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
        model.addAttribute("milestone", milestoneService.find(milestoneId));
        model.addAttribute("username", username);
        return "milestones/edit";
    }

    @PostMapping("{username}/{milestoneId}/edit")
    public String showEdit(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") long milestoneId,
        @Validated MilestoneForm form,
        BindingResult bindingResult, Model model
    ) {
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
        return String.format("redirect:/%s", username);
    }
    
    @GetMapping("{username}/dump")
    @ResponseBody
    public String dump(
        @ModelAttribute MilestoneForm form,
        @PathVariable("username") String username,
        Model model
    ) {
        return milestoneService.findByUser(username).toString();
    }
}
