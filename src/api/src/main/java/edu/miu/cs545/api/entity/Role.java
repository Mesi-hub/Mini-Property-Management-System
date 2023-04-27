package edu.miu.cs545.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    String role;
    String description;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<User> users;

}
