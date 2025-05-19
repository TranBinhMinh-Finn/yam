package com.yam.backend.config;

import com.yam.backend.model.user.User;
import com.yam.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppInit implements CommandLineRunner {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${security.default-admin-password}")
    private String defaultAdminPassword;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() > 0)
            return;

        User admin = new User();
        admin.setEmail("admin@yam.com");
        admin.setPassword(passwordEncoder.encode(defaultAdminPassword));
        admin.setName("admin");
        admin.setRole(User.Role.ADMIN.toString());

        userRepository.save(admin);
    }
}
