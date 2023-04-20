package edu.miu.cs545.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccessToken {
    @Id
    String token;
    @OneToOne
    User user;
    @ManyToOne(cascade = CascadeType.ALL)
    RefreshToken refreshToken;
}
