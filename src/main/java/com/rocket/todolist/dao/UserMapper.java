package com.rocket.todolist.dao;


import com.rocket.todolist.beam.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(int id);

    User getUserByEmail(String email);
}
