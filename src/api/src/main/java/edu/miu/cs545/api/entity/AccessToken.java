package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    RefreshToken refreshToken;
}
