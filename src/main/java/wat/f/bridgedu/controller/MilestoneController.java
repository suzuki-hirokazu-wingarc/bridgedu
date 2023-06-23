package wat.f.bridgedu.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import wat.f.bridgedu.domain.MilestoneForm;
import wat.f.bridgedu.domain.MilestoneService;

@AllArgsConstructor
@Controller
@RequestMapping("milestones")
public class MilestoneController {
    private MilestoneService milestoneService;// = new MilestoneService();
    @GetMapping
    public String showList(Model model) {
        model.addAttribute("milestoneList", milestoneService.findAll());
        return "milestones/list";
    }

    @PostMapping
    public String create(
        @Validated MilestoneForm form,
        BindingResult bindingResult, Model model
    ) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()) {
            return showCreationForm(form);
        }
        
        milestoneService.create(
            user.getUsername(),
            form.getTitle(),
            form.getMemo(),
            form.getImportance(),
            form.getArchivement(),
            form.getGoal(),
            Date.valueOf(LocalDate.now()),
            Date.valueOf(LocalDate.now())
        );
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
}
