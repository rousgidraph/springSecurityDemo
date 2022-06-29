package com.gidraph.springsecuritydemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name ;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) // load the user and all their roles from the database
    private Collection<Role> roles = new ArrayList<>();

}
