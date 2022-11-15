package com.example.course.repository;

import com.example.course.model.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users;
    private final Faker faker;

    private UserRepository(Faker faker) {
        this.faker = faker;
        initUsers();
    }

    private void initUsers() {
        users = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            User user = new User(
                    i,
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().phoneNumber(),
                    faker.avatar().image());
            users.add(user);
        }
    }

    public List<User> findAll() {
        return users;
    }
}
