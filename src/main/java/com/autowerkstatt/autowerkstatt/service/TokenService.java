package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.TokenRepository;
import com.autowerkstatt.autowerkstatt.entity.Token;
import com.autowerkstatt.autowerkstatt.entity.Users;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TokenService {

    TokenRepository repo;

    public Integer makeToken() {
        return new Random().nextInt(900000) + 100000;
    }


    public Token getToken(Integer token) {
        return repo.findByToken(token)
                .orElse(null);
    }

    public Token saveToken(Users user, Integer tokenCode) {
        Token token = Token.builder()
                .time(new Date(System.currentTimeMillis()))
                .token(tokenCode)
                .user(user)
                .build();
        repo.save(token);
        return token;
    }

    public void deleteToken(Token token) {
        repo.delete(token);
    }

    public Token findByUserAndToken(Users user, Integer token) {
        return repo.findByTokenAndUser(token, user)
                .orElseThrow(() -> new NoSuchElementException(String.format("Не найден токен %s для пользователя", token, user.getEmail())));

    }
}
