package edu.miu.cs545.api.config;

import edu.miu.cs545.api.entity.RoleTypes;
import edu.miu.cs545.api.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    String [] roles = {RoleTypes.ADMIN.toString(), RoleTypes.OWNER.toString(), RoleTypes.CUSTOMER.toString()};
    String [] unsecuredUrls = {"/authenticate", "/authenticate/refresh"};
    String [] genericLoggedInUserUrls = {"/authenticate/userinfo", "/authenticate/logoff"};
    String [] customerUrls = {};
    String [] ownerUrls = {};
    String [] adminUrls = {"/administrators"};

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable().cors().and()
                .authorizeHttpRequests()

                //Comment the lines after this line to bypass authentication during dev
                .requestMatchers(unsecuredUrls).permitAll()
                .requestMatchers(genericLoggedInUserUrls).hasAnyAuthority(roles)
                .requestMatchers(customerUrls).hasAuthority(RoleTypes.CUSTOMER.toString())
                .requestMatchers(ownerUrls).hasAuthority(RoleTypes.OWNER.toString())
                .requestMatchers(adminUrls).hasAuthority(RoleTypes.ADMIN.toString())
                //Comment the lines upto this line to bypass authentication during dev

                // below line should be uncommented during dev to bypass authentication
                //.anyRequest().permitAll()

                //below line should be commented during dev to bypass authentication
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("GET", "PUT", "POST","DELETE");
            }
        };
    }
}
