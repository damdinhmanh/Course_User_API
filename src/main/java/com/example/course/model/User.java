package com.example.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
}
