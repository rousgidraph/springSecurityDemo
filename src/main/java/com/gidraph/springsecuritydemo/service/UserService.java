package com.gidraph.springsecuritydemo.service;

import com.gidraph.springsecuritydemo.domain.Role;
import com.gidraph.springsecuritydemo.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();

}
