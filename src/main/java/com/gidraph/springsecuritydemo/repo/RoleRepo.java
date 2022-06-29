package com.gidraph.springsecuritydemo.repo;

import com.gidraph.springsecuritydemo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
        Role findByName(String name);
    }


