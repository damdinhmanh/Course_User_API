package com.example.course.service;

import com.example.course.model.Course;
import com.example.course.model.User;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.UserRepository;
import com.example.course.request.UpsertCourseRequest;
import com.example.course.response.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationContext context;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public UserCourse getCourseWithUserInfo(int courseId) {
        List<Course> courses = courseRepository.findAll();
        UserCourse userCourse = context.getBean("userCourseInfo", UserCourse.class);
        userCourse.setCourse(null);
        userCourse.setUser(null);

        Optional<Course> course = courses.
                        stream().
                        filter(coursei -> coursei.getId() == courseId)
                        .findFirst();

        if (course.isPresent()) {
            List<User> users = userRepository.findAll();
            Course aCourse = course.get();
            Optional<User> user = users.
                                stream().
                                filter(useri -> useri.getId() == aCourse.getUserId())
                                .findFirst();
            if (user.isPresent()) {
                userCourse.setCourse(aCourse);
                userCourse.setUser(user.get());
            }
        }

        return userCourse;
    }

    public Course createCourse(UpsertCourseRequest request) {
        List<Course> courses = courseRepository.findAll();
        Course lastIndexCourse = courses.get(courses.size() - 1);
        Course course = new Course(lastIndexCourse.getId() + 1, request.getName(), request.getDescription(),
                                    request.getType(), request.getTopics(), request.getThumbnail(), request.getUserId());

        courseRepository.save(course);
        return course;
    }

    public Course getCourse(int id) {
        List<Course> courses = courseRepository.findAll();
        Optional<Course> course = courses.
                stream().
                filter(coursei -> coursei.getId() == id)
                .findFirst();

        if (course.isPresent()) {
            return course.get();
        }
        return null;
    }

    public Course updateCourse(int courseId, UpsertCourseRequest request) {
        return courseRepository.update(courseId, request.getName(), request.getDescription(), request.getType(),
                                       request.getTopics(), request.getThumbnail(), request.getUserId());
    }

    public void deleteCourse(int courseId) {
        courseRepository.delete(courseId);
    }
}
