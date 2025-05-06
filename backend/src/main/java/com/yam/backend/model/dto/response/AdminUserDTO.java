package com.yam.backend.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserDTO {
    private long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String role;

    private boolean deleted;

    private boolean active;
}
