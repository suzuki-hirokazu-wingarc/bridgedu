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

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.controller.form.TaskForm;
import wat.f.bridgedu.domain.entity.TagEntity;
import wat.f.bridgedu.domain.entity.TaskEntity;
import wat.f.bridgedu.domain.entity.UserDetailsImpl;
import wat.f.bridgedu.domain.service.TaskService;

@RequiredArgsConstructor
@Controller
// @RequestMapping("milestones")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("{username}/{id}/creation")
    public String showCreationForm(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("id") long milestoneId,
        @ModelAttribute TaskForm form,
        Model model
    ) {
        model.addAttribute("milestoneId", milestoneId);
        return "milestones/tasks/create.html";
    }

    @PostMapping("{username}/{id}/creation")
    public String create(
        @AuthenticationPrincipal UserDetailsImpl user,
        @Validated TaskForm form,
        @PathVariable("username") String username,
        @PathVariable("id") long milestoneId,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(user, username, milestoneId, form, model);
        }
        TaskEntity task = new TaskEntity(
            0,
            milestoneId,
            form.getName(),
            form.getImportance(),
            form.getAchievement(),
            new TagEntity(1, null, null)
        );
        taskService.create(task);
        return String.format("redirect:/%s/%d", username, milestoneId); /// TODO 暫定
    }

    @GetMapping("tasks")
    @ResponseBody
    public String dumpAllTasks() {
        return taskService.findAll().toString();
    }
}
