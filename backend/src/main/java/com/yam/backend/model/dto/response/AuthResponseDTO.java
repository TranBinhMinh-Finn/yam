package com.yam.backend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private String accessToken;

    private String refreshToken;
}
