package com.yam.backend.service;

import com.yam.backend.exception.RequestException;
import com.yam.backend.model.dto.request.RegisterDTO;
import com.yam.backend.model.user.User;
import com.yam.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User register(RegisterDTO registerDTO) {
        if (userRepository.findUserByEmailAndDeleted(registerDTO.getEmail(), false).isPresent()) {
            throw new RequestException("User with email " + registerDTO.getEmail() + " already exists");
        }

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setName(registerDTO.getName());

        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmailAndDeleted(email, false)
                .orElseThrow(() -> new RequestException("User does not exist"));
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RequestException("User does not exist"));
    }

    public Page<User> getUsersForAdmin(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        User user = findById(id);
        user.setDeleted(true);
        userRepository.save(user);
    }
}
