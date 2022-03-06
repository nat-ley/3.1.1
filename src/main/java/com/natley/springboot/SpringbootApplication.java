package com.natley.springboot;

import com.natley.springboot.model.Role;
import com.natley.springboot.model.User;
import com.natley.springboot.service.RoleService;
import com.natley.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootApplication implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.save(new Role("admin"));
        roleService.save(new Role("user"));
        userService.save(User.builder()
                .firstName("testadmin")
                .lastName("admintest")
                .email("admin")
                .password("123")
                .roles(Set.of(roleService.findByName("admin")))
                .build());
        userService.save(User.builder()
                .firstName("testuser")
                .lastName("usertest")
                .email("user")
                .password("123")
                .roles(Set.of(roleService.findByName("user")))
                .build());
    }
}
