package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
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
    @JsonBackReference(value="user-roles")
    List<Role> roles;

    @OneToMany(mappedBy = "user")
    List<RefreshToken> refreshTokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        roles.stream().forEach(x-> {
            Person person = this.person;
            if(person.getClass()==Owner.class && ((Owner)person).approved){
                authorityList.add((GrantedAuthority) () -> x.getRole());
            }
            if(person.getClass()!=Owner.class)
            {
                authorityList.add((GrantedAuthority) () -> x.getRole());
            }
        });
        return authorityList;
    }
    @OneToOne(mappedBy = "user")
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
        return !this.person.isBlackListed();
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
