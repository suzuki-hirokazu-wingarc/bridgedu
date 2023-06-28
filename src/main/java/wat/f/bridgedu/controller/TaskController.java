package wat.f.bridgedu.controller;

import java.util.NoSuchElementException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.controller.form.TaskForm;
import wat.f.bridgedu.domain.entity.UserDetailsImpl;
import wat.f.bridgedu.domain.service.TagService;
import wat.f.bridgedu.domain.service.TaskService;

@RequiredArgsConstructor
@Controller
// @RequestMapping("milestones")
public class TaskController {
    private final TaskService taskService;
    private final TagService tagService;

    @GetMapping("{username}/{id}/creation")
    public String showCreationForm(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("id") long milestoneId,
        @ModelAttribute TaskForm form,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        model.addAttribute("milestoneId", milestoneId);
        model.addAttribute("tagList", tagService.findAll());
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
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        if (bindingResult.hasErrors()) {
            return showCreationForm(user, username, milestoneId, form, model);
        }
        try {
            taskService.create(
                milestoneId,
                form.getName(),
                form.getImportance(),
                form.getAchievement(),
                tagService.find(form.getTagId())
            );
        } catch (NoSuchElementException e) {
            return showCreationForm(user, username, milestoneId, form, model);
        }
        return String.format("redirect:/%s/%d", username, milestoneId); /// TODO 暫定
    }

    @GetMapping("{username}/{milestoneId}/{taskId}/edit")
    public String showEdit(
        @AuthenticationPrincipal UserDetailsImpl user,
        @ModelAttribute TaskForm form,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") Long milestoneId,
        @PathVariable("taskId") Long taskId,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        var task = taskService.find(taskId);

        form.setName(task.getName());
        form.setAchievement(task.getAchievement());
        form.setImportance(task.getImportance());
        form.setTagId(task.getTag().getId());

        model.addAttribute("username", username);
        model.addAttribute("milestoneId", milestoneId);
        model.addAttribute("tagList", tagService.findAll());
        return "milestones/tasks/edit";
    }

    @PostMapping("{username}/{milestoneId}/{taskId}/edit")
    public String showEdit(
        @AuthenticationPrincipal UserDetailsImpl user,
        @PathVariable("username") String username,
        @PathVariable("milestoneId") Long milestoneId,
        @PathVariable("taskId") Long taskId,
        @Validated TaskForm form,
        BindingResult bindingResult,
        Model model
    ) {
        if (AccessControlUtils.isNotAccessibleStudentPage(user, username))
            return "forbidden";
        if (bindingResult.hasErrors()){
            return showEdit(user, form, username, milestoneId, taskId, model);
        }

        taskService.update(
            taskId,
            form.getName(),
            form.getImportance(),
            form.getAchievement(),
            taskService.find(form.getTagId()).getTag()
        );
        return String.format("redirect:/%s/%s", username, milestoneId);
    }

    // @GetMapping("tasks")
    // @ResponseBody
    // public String dumpAllTasks() {
    //     return taskService.findAll().toString();
    // }
}
