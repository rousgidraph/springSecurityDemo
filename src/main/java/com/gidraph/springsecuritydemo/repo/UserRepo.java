package com.gidraph.springsecuritydemo.repo;

import com.gidraph.springsecuritydemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
