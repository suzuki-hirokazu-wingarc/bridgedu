package wat.f.bridgedu.controller;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import wat.f.bridgedu.domain.entity.UserDetailsImpl;

@Controller
public class IndexController {
    @GetMapping
    public String index(
        @AuthenticationPrincipal UserDetailsImpl user,
        Model model
    ) {
        model.addAttribute("username", user.getUsername());
        String authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(""));
        if (authorities.contains("STUDENT")) {
            return String.format("redirect:/%s", user.getUsername());
        } else {
            return "redirect:/students";
        }
        // return "index";
    }
    @GetMapping("login")
    public String showLoginForm() {
        return "login";
    }
    @GetMapping("logout")
    public String showLogoutForm() {
        return "logout";
    }
}
