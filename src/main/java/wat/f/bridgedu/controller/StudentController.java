package wat.f.bridgedu.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import wat.f.bridgedu.domain.entity.UserDetailsImpl;
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
    public String list(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("loginUser", userService.find(user.getUsername()));
        model.addAttribute("userList", userService.findByRole(UserRole.ROLE_STUDENT));
        return "users/list";
    }
    
    @GetMapping("creation")
    public String showCreation(@AuthenticationPrincipal UserDetailsImpl user, @ModelAttribute UserForm form, Model model) {
        model.addAttribute("loginUser", userService.find(user.getUsername()));
        return "users/create";
    }

    @PostMapping("creation")
    public String create(@AuthenticationPrincipal UserDetailsImpl user, @Validated UserForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.toString());
            return showCreation(user, form, model);
        }
        try {
            userService.create(
                form.getName(),
                form.getDisplayName(),
                passwordEncoder.encode(form.getPassword()),
                true,
                new SerialBlob(form.getIcon().getBytes())
            );
        } catch (AlreadyExistUsernameException e) {
            result.rejectValue("name", "error.name.exist", "the username is already exists.");
            return showCreation(user, form, model);
        } catch (SerialException e) {
            e.printStackTrace();
            result.rejectValue("icon", "error.icon.invalid", "the usericon is invalid.");
            return showCreation(user, form, model);
        } catch (SQLException e) {
            e.printStackTrace();
            result.rejectValue("icon", "error.icon.invalid", "the usericon is invalid.");
            return showCreation(user, form, model);
        } catch (IOException e) {
            e.printStackTrace();
            result.rejectValue("icon", "error.icon.invalid", "the usericon is invalid.");
            return showCreation(user, form, model);
        }
        return "redirect:/students";
    }
}
