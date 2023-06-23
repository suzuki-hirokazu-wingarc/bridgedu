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
import wat.f.bridgedu.domain.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "users/list";
    }
    
    @GetMapping("create")
    public String create(@ModelAttribute UserForm form) {
        return "users/create";
    }

    @PostMapping
    public String create(@Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model);
        }
        userService.create(
            form.getName(),
            form.getDisplayName(),
            passwordEncoder.encode(form.getPassword()),
            true);
        return "redirect:/users";
    }
}
