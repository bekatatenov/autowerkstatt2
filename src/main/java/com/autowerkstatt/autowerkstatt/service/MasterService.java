package com.autowerkstatt.autowerkstatt.service;
import com.autowerkstatt.autowerkstatt.dao.MasterRepository;
import com.autowerkstatt.autowerkstatt.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {

    @Autowired
    private MasterRepository masterRepository;

    public void save(Master master){
        this.masterRepository.save(master);
    }

    public void delete(Long id) {
        this.masterRepository.deleteById(id);
    }

    public List<Master> allMasters() {
        return this.masterRepository.findAll();
    }

    public void updateMasterProfile(Long id, String lastName, String firstName, String Faults) {

    }

    public Master getByName(String firstName){
        return masterRepository.findByFirstName(firstName);
    }
}
