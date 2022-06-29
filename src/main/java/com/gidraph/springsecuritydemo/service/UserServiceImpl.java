package com.gidraph.springsecuritydemo.service;

import com.gidraph.springsecuritydemo.domain.Role;
import com.gidraph.springsecuritydemo.domain.User;
import com.gidraph.springsecuritydemo.repo.RoleRepo;
import com.gidraph.springsecuritydemo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null){
            log.error("User not found in db");
            throw new UsernameNotFoundException("User not found in db");
        }else{
            log.info("User found in db => {}",user);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    private final RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving user to database {}",user);
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role to database {}",role);

        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);

        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("Getting user from database {}",username);

        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepo.findAll();
    }
}
