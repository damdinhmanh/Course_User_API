package com.example.course;

import com.example.course.model.Course;
import com.example.course.response.UserCourse;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean("retCourseList")
    public List<Course> courses() {
        return new ArrayList<Course>();
    }

    @Bean("userCourseInfo")
    public UserCourse userCourseInfor() {
        return new UserCourse();
    }

}
