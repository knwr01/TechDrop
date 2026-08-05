package ca.humber.techdrop.controller;

import ca.humber.techdrop.dto.RegisterRequest;
import ca.humber.techdrop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registerRequest") RegisterRequest registerRequest,
            BindingResult result) {

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
        }

        if (userService.usernameExists(registerRequest.getUsername())) {
            result.rejectValue("username", "username.taken", "That username is already taken");
        }

        if (userService.emailExists(registerRequest.getEmail())) {
            result.rejectValue("email", "email.taken", "That email is already registered");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.registerCustomer(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword());

        return "redirect:/login?registered";
    }
}
