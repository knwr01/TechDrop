package ca.humber.techdrop.controller;

import ca.humber.techdrop.model.Role;
import ca.humber.techdrop.model.User;
import ca.humber.techdrop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", Role.values());
        return "users";
    }

    @PostMapping("/users/{id}/role")
    public String changeUserRole(
            @PathVariable Long id,
            @RequestParam Role role,
            @AuthenticationPrincipal User currentUser) {

        if (currentUser != null && currentUser.getUserId().equals(id)) {
            return "redirect:/users?selfChange";
        }

        userService.updateUserRole(id, role);
        return "redirect:/users";
    }
}
