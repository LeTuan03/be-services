package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tbl_account")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 1, message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 1, message = "Password cannot be empty")
    private String password;


    @NotNull(message = "Role cannot be null")
    @Size(min = 1, message = "Role cannot be empty")
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects;
}