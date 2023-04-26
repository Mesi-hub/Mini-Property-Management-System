package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class RefreshToken {
    @Id
    String token;
    @ManyToOne
    User user;
    @OneToMany(mappedBy = "refreshToken", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    List<AccessToken> accessTokens;
}
