package com.autowerkstatt.autowerkstatt.models;

import com.autowerkstatt.autowerkstatt.entity.Models;
import com.autowerkstatt.autowerkstatt.enums.Marks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarSelectModel {
    Marks mark;
    List<Models> models = new ArrayList<>();
}
