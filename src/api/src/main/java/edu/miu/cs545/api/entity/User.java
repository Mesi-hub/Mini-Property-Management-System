package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
//Postgres cannot create user table
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(unique = true)
    @NotEmpty
    @Email
    String email;
    String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    List<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    List<RefreshToken> refreshTokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(x-> (GrantedAuthority) x::getRole).toList();
    }
    @OneToOne(mappedBy = "user")
    @JsonBackReference
    Person person;
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true ;
    }
    @Override
    public String toString(){
        return "Id: " + id + " name: " + name;
    }
}
