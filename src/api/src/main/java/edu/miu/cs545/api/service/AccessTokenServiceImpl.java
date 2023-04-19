package edu.miu.cs545.api.service;

import edu.miu.cs545.api.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{
    @Autowired
    AccessTokenRepository accessTokenRepository;
    @Override
    public boolean accessTokenExists(String token) {
        return accessTokenRepository.existsById(token);
    }
}
