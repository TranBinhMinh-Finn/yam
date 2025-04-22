package com.yam.backend.service;

import com.yam.backend.model.dto.request.RegisterDTO;
import com.yam.backend.model.user.User;
import com.yam.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User register(RegisterDTO registerDTO) {
        if (userRepository.findUserByEmail(registerDTO.getEmail()).isPresent()) {
            throw new RuntimeException("User with" + registerDTO.getEmail() + " already exists");
        }

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setName(registerDTO.getName());

        return userRepository.save(user);
    }
}
