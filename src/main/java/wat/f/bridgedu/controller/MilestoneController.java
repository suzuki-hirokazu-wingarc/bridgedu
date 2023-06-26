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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import wat.f.bridgedu.domain.MilestoneEntity;
import wat.f.bridgedu.domain.MilestoneService;
import wat.f.bridgedu.domain.UserDetailsImpl;

@AllArgsConstructor
@Controller
@RequestMapping("milestones")
public class MilestoneController {
    private MilestoneService milestoneService;// = new MilestoneService();
    
    @GetMapping
    public String showList(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("milestoneList", milestoneService.findByUser(user.getUsername()));
        return "milestones/list";
    }
    
    @PostMapping
    public String create(
        @AuthenticationPrincipal UserDetailsImpl user,
        @Validated MilestoneForm form,
        BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form);
        }

        MilestoneEntity milestone = new MilestoneEntity(0,
            user.getUsername(), form.getTitle(), form.getMemo(), form.getImportance(), form.getArchivement(),
            form.getGoal(), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), Collections.emptyList());
        milestoneService.create(milestone);
        return "redirect:/milestones";
    }

    @GetMapping("create")
    public String showCreationForm(@ModelAttribute MilestoneForm form) {
        return "milestones/create";
    }
    
    @GetMapping("{id}")
    public String showDetail(@PathVariable("id") long id, Model model) {
        model.addAttribute("milestone", milestoneService.find(id));
        return "milestones/detail";
    }
    
    @GetMapping("dump")
    @ResponseBody
    public String dump(@AuthenticationPrincipal UserDetailsImpl user,Model model) {
        return milestoneService.findByUser(user.getUsername()).toString();
    }
}
