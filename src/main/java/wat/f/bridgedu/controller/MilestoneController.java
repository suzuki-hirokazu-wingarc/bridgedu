package wat.f.bridgedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
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

    // @PostMapping
    // public String create(@Validated MilestoneForm form, BindingResult bindingResult, Model model) {
    //     if (bindingResult.hasErrors()) {
    //         return showCreationForm(form);
    //     }
    //     milestoneService.create(form.getHeader(), form.getDescription());
    //     return "redirect:/milestones";
    // }

    // @GetMapping("creationForm")
    // public String showCreationForm(@ModelAttribute MilestoneForm form) {
    //     return "milestones/creationForm";
    // }
    
    @GetMapping("{id}")
    public String showDetail(@PathVariable("id") long id, Model model) {
        model.addAttribute("milestone", milestoneService.find(id));
        return "milestones/detail";
    }
}
