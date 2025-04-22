package com.yam.backend.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
