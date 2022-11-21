package com.autowerkstatt.autowerkstatt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String userEmail;
    private Integer token;
    private String password;
}
