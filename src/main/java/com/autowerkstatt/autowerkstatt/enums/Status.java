package com.autowerkstatt.autowerkstatt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Status {

    NEW("НОВЫЙ"),
    PENDING("В ОЖИДАНИИ"),
    DONE("ВЫПОЛНЕННО"),
    DENIED("ОТКАЗАНО");

    private String translate;
}
