package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.ModelRepository;
import com.autowerkstatt.autowerkstatt.entity.Models;
import com.autowerkstatt.autowerkstatt.enums.Marks;
import com.autowerkstatt.autowerkstatt.models.CarSelectModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ModelsService {
    private  ModelRepository repository;


    public List<CarSelectModel> getAllForSelect(){
        List<CarSelectModel> list = new ArrayList<>();
        Marks[] marks = Marks.values();
        for (Marks mark : marks) {
            list.add(new CarSelectModel(mark, repository.findAllByMarks(mark)));
        }
        return list;
    }
}
