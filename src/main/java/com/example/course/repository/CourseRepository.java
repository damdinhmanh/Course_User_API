package com.example.course.repository;

import com.example.course.model.Course;
import com.example.course.model.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class CourseRepository {
    private List<Course> courses;
    private final Faker faker;
    private final UserRepository userRepository;

    public CourseRepository(Faker faker, UserRepository userRepository) {
        this.faker = faker;
        this.userRepository = userRepository;

        initCourses();
    }

    private void initCourses() {
        courses = new ArrayList<>();

        Random rd = new Random();
        List<String> topics = List.of("frontend", "backend", "database", "devops", "AWS", "basic", "mobile");
        List<User> users = userRepository.findAll();

        for (int i = 1; i < 21; i++) {
            // Random topic
            List<String> rdTopics = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                String rdTopic = topics.get(rd.nextInt(topics.size()));
                if(!rdTopics.contains(rdTopic)) {
                    rdTopics.add(rdTopic);
                }
            }

            // Random user
            User rdUser = users.get(rd.nextInt(users.size()));

            // Tạo khóa học
            Course course = new Course(
                    i,
                    faker.funnyName().name(),
                    faker.lorem().sentence(20),
                    rd.nextInt(2) == 1 ? "online" : "onlab",
                    rdTopics,
                    faker.avatar().image(),
                    rdUser.getId()
            );

            courses.add(course);
        }
    }

    public List<Course> findAll() {
        return courses;
    }

    public void save(Course course) {
        courses.add(course);
    }

    public Course update(int id, String name,  String description, String type, List<String> topics,
                       String thumbnail, int userId) {
        Optional<Course> course = courses.
                stream().
                filter(coursei -> coursei.getId() == id)
                .findFirst();

        if (course.isPresent()) {
            Course aCourse = course.get();
            aCourse.setName(name);
            aCourse.setDescription(description);
            aCourse.setType(type);
            aCourse.setTopics(topics);
            aCourse.setThumbnail(thumbnail);
            aCourse.setUserId(userId);
            return aCourse;
        }
        return null;
    }

    public void delete(int id) {
        Optional<Course> course = courses.
                stream().
                filter(coursei -> coursei.getId() == id)
                .findFirst();

        if (course.isPresent()) {
            courses.remove(course.get());
        }
    }
}
