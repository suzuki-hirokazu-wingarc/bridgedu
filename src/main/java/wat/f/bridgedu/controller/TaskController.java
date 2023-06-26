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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.domain.MilestoneService;
import wat.f.bridgedu.domain.TaskEntity;
import wat.f.bridgedu.domain.TaskService;
import wat.f.bridgedu.domain.UserDetailsImpl;

@RequiredArgsConstructor
@Controller
@RequestMapping("milestones")
public class TaskController {
    private final MilestoneService milestoneService;
    private final TaskService taskService;

    @GetMapping("{id}/create")
    public String showCreationForm(@PathVariable("id") long id, @ModelAttribute TaskForm form, Model model) {
        model.addAttribute("milestoneId", id);
        return "milestones/tasks/create.html";
    }

    @PostMapping("{id}/create")
    public String create(
        @AuthenticationPrincipal UserDetailsImpl user,
        @Validated TaskForm form,
        BindingResult bindingResult,
        @PathVariable("id") long id,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(id, form, model);
        }
        TaskEntity task = new TaskEntity(
            0,
            id,
            form.getName(),
            form.getDescription(),
            form.getImportance(),
            form.getAchievement()
        );
        taskService.create(task);
        return "redirect:/milestones/tasks"; /// TODO 暫定
    }

    @GetMapping("tasks")
    @ResponseBody
    public String dumpAllTasks() {
        return taskService.findAll().toString();
    }
}
