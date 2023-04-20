package edu.miu.cs545.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class JwtResponseDto implements Serializable {
    private final String token;
    private final Date expiresAt;
    private final String refreshToken;
    private final Date refreshTokenExpiresAt;
}
