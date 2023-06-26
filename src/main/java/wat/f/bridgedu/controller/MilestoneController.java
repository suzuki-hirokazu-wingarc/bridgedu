package wat.f.bridgedu.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;

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
import wat.f.bridgedu.domain.entity.MilestoneEntity;
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
        model.addAttribute("username", username);
        model.addAttribute("milestoneList", milestoneService.findByUser(user.getUsername()));
        return "milestones/list";
    }
    
    @PostMapping("{username}")
    public String create(
        @AuthenticationPrincipal UserDetailsImpl user,
        @Validated MilestoneForm form,
        @PathVariable("username") String username,
        BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form, username, model);
        }

        MilestoneEntity milestone = new MilestoneEntity(0,
            user.getUsername(), form.getTitle(), form.getMemo(), form.getImportance(), form.getAchievement(),
            form.getGoal(), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Collections.emptyList());
        milestoneService.create(milestone);
        return "redirect:/milestones";
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
    
    @GetMapping("{username}/dump")
    @ResponseBody
    public String dump(@AuthenticationPrincipal UserDetailsImpl user,Model model) {
        return milestoneService.findByUser(user.getUsername()).toString();
    }
}
