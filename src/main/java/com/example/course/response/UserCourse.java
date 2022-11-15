package com.example.course.response;

import com.example.course.model.Course;
import com.example.course.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCourse {
    private Course course;
    private User user;
}
