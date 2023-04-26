package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.AuthDto;
import edu.miu.cs545.api.dto.JwtResponseDto;
import edu.miu.cs545.api.dto.RefreshDto;
import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    ControllerSecurityUtil controllerSecurityUtil;
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<JwtResponseDto> createToken(@RequestBody AuthDto
                                                request) throws Exception {
        try {
            daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return ResponseEntity.ok(authService.getJwtTokens(request.getUsername()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> refresh(@RequestBody RefreshDto refreshDto){
        return ResponseEntity.ok(authService.refresh(refreshDto));
    }

    @RequestMapping("/logout")
    public ResponseEntity<Void> logout(){
        User user = controllerSecurityUtil.getLoggedinUser();
        authService.logout(user.getUsername());
        return ResponseEntity.ok().build();
    }
    @RequestMapping("/userinfo")
    public ResponseEntity<UserDto> getUserInfo(){
        User user = controllerSecurityUtil.getLoggedinUser();
        if(user != null) {
            return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
        }
        return ResponseEntity.badRequest().build();
    }
}
