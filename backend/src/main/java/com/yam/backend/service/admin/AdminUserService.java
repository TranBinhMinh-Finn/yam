package com.yam.backend.service.admin;

import com.yam.backend.exception.RequestException;
import com.yam.backend.model.dto.response.AdminUserDTO;
import com.yam.backend.model.user.User;
import com.yam.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserService userService;

    private final ModelMapper modelMapper;

    public Page<AdminUserDTO> getUsers(int pageSize,
                                       int PageNumber) {
        Pageable pageable = PageRequest.of(pageSize, PageNumber);
        Page<User> result = userService.getUsersForAdmin(pageable);
        return result.map(user -> modelMapper.map(user, AdminUserDTO.class));
    }

    public void changeUserActiveStatus(long id, boolean active) {
        User user = userService.findById(id);
        user.setActive(active);
        userService.updateUser(user);
    }

    public void changeUserRole(long id, String role) {
        User user = userService.findById(id);
        for (User.Role roleEnum : User.Role.values()) {
            if (roleEnum.toString().equals(role)) {
                user.setRole(role);
                userService.updateUser(user);
                return;
            }
        }
        throw new RequestException("Invalid role");
    }
}
