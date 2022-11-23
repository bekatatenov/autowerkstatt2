package com.autowerkstatt.autowerkstatt.dao;

import com.autowerkstatt.autowerkstatt.entity.Models;
import com.autowerkstatt.autowerkstatt.enums.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Models, Long> {
    List<Models> findAllByMarks(Marks marks);
}
