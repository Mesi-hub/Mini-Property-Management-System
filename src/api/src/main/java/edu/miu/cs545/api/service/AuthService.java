package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.JwtResponseDto;
import edu.miu.cs545.api.dto.RefreshDto;

public interface AuthService {
    JwtResponseDto getJwtTokens(String username);
    JwtResponseDto refresh(RefreshDto refreshDto);
    void logout(String name);
}
