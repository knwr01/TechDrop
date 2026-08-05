package ca.humber.techdrop.service;

import ca.humber.techdrop.model.Role;
import ca.humber.techdrop.model.User;
import ca.humber.techdrop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User registerCustomer(String username, String email, String rawPassword) {
        User user = new User(username, email, passwordEncoder.encode(rawPassword), Role.CUSTOMER);
        return userRepository.save(user);
    }
}
