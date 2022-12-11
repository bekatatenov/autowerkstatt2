package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.TurnRepository;
import com.autowerkstatt.autowerkstatt.entity.Turn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnService {

    @Autowired
    private TurnRepository turnRepository;

    public void save(Turn turn) {
        this.turnRepository.save(turn);
    }
}
