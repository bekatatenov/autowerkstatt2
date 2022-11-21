package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.UsersRepository;
import com.autowerkstatt.autowerkstatt.entity.Users;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.minidev.asm.ex.NoSuchFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    public void save(Users user) {
        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Users user = userRepository.findFirstByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));


        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    public Users findByEmailUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchFieldException("Не найден пользователь по email: " + email));
    }
}
