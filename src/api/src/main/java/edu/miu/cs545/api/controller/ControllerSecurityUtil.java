package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ControllerSecurityUtil {
    public User getLoggedinUser() {
        if(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal() != null
                && SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal() instanceof UserDetails) {

            return (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        }
        return null;
    }
}
