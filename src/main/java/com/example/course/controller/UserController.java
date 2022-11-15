package com.example.course.controller;

import com.example.course.model.Course;
import com.example.course.request.UpsertCourseRequest;
import com.example.course.response.UserCourse;
import com.example.course.service.CourseService;
import com.example.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.*;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext context;

    //API User -------------------------------------------------------------------
    //GET /api/v1/courses?type={typeValue}&name={nameValue}&topic={topicValue} : Xem danh sách tất cả khóa học
    @GetMapping("courses")
    public List<Course> userGetCourses(@RequestParam String type,
                                       @RequestParam String name, @RequestParam String topic) {
        List<Course> courses = courseService.getCourses();
        List<Course> retCourses = (List<Course>) context.getBean("retCourseList", Object.class);
        retCourses.clear();

        for (Course course : courses) {
            if (course.getType().equals(type) || course.getName().equals(name)) {
                retCourses.add(course);
            } else {
                for(String topicIter : course.getTopics()) {
                    if (topicIter.equals(topic)) {
                        retCourses.add(course);
                    }
                }
            }
        }

        return retCourses;
    }

    //GET /api/v1/courses/{id} : thông tin của 1 khóa học cụ thể ( bao gồm thông tin khóa học và nhân viên tư vấn)
    @GetMapping("courses/{id}")
    public UserCourse userGetCourseById(@PathVariable int id) {
        return courseService.getCourseWithUserInfo(id);
    }


    //API Admin -----------------------------------------------------------------
    //GET /api/v1/admin/courses : Xem danh sách khóa học
    @GetMapping("admin/courses")
    public List<Course> adminGetCourses() {
        return courseService.getCourses();
    }

    //POST /api/v1/admin/courses : Tạo khóa học mới
    @PostMapping("admin/courses")
    public Course adminCreateCourse(@Valid @RequestBody UpsertCourseRequest request) {
        return courseService.createCourse(request);
    }

    //GET /api/v1/admin/courses/{id} : Lấy chi tiết khóa học
    @GetMapping("admin/courses/{id}")
    public Course adminGetCourseById(@PathVariable int id) {
        return courseService.getCourse(id);
    }

    //PUT /api/v1/admin/courses/{id} : Cập nhật thông tin khóa học
    @PutMapping("admin/courses/{id}")
    public Course adminUpdateCourseById(@PathVariable int id, @Valid @RequestBody UpsertCourseRequest request) {
        return courseService.updateCourse(id, request);
    }

    //DELETE /api/v1/admin/courses/{id} : Xóa khóa học
    @DeleteMapping("admin/courses/{id}")
    public void adminDeleteCourseById(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
