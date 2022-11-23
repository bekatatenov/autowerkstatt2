package com.autowerkstatt.autowerkstatt.utils;

import com.autowerkstatt.autowerkstatt.dao.UsersRepository;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.enums.Roles;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@AllArgsConstructor
public class CreateAdmin {
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner run(UsersRepository usersRepository) {

        return (args) -> {
            List<Users> usersList = usersRepository.findAll();
            if (usersList.isEmpty()) {
                Users admin = Users
                        .builder()
                        .email("admin@gmail.com")
                        .firstName("Admin")
                        .lastName("Adminov")
                        .password(passwordEncoder.encode("admin"))
                        .active(true)
                        .telNumber(555776087)
                        .role(Roles.ADMIN)
                        .build();
                usersRepository.save(admin);
            }
        };
    }
}
