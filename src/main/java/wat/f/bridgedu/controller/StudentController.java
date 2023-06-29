package wat.f.bridgedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import wat.f.bridgedu.controller.form.UserForm;
import wat.f.bridgedu.domain.service.UserService;
import wat.f.bridgedu.domain.service.exception.AlreadyExistUsernameException;

@RequiredArgsConstructor
@Controller
@RequestMapping("students")
public class StudentController {
    private final UserService userService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("userList", userService.findByRole(UserRole.ROLE_STUDENT));
        return "users/list";
    }
    
    @GetMapping("creation")
    public String showCreation(@ModelAttribute UserForm form, Model model) {
        return "users/create";
    }

    @PostMapping("creation")
    public String create(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return showCreation(form, model);
        }
        try {
            userService.create(
                form.getName(),
                form.getDisplayName(),
                passwordEncoder.encode(form.getPassword()),
                true
            );
        } catch (AlreadyExistUsernameException e) {
            result.rejectValue("name", "error.name.exist", "the username is already exists.");
            return showCreation(form, model);
        }
        return "redirect:/students";
    }
}
