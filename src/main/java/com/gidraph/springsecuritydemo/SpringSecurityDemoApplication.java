package com.gidraph.springsecuritydemo;

import com.gidraph.springsecuritydemo.domain.Role;
import com.gidraph.springsecuritydemo.domain.User;
import com.gidraph.springsecuritydemo.service.UserService;
import com.gidraph.springsecuritydemo.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserServiceImpl userService){
        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null,"John","john","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Will Smith","will","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Isack Danielson","Isack","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Kanye West","Kanye","1234",new ArrayList<>()));

            userService.addRoleToUser("john","ROLE_USER");
            userService.addRoleToUser("john","ROLE_ADMIN");
            userService.addRoleToUser("john","ROLE_SUPER_ADMIN");

            userService.addRoleToUser("will","ROLE_USER");
            userService.addRoleToUser("will","ROLE_MANAGER");

            userService.addRoleToUser("Isack","ROLE_USER");

            userService.addRoleToUser("Kanye","ROLE_USER");


        };
    }
}
