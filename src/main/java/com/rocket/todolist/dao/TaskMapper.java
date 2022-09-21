package com.rocket.todolist.dao;

import com.rocket.todolist.beam.Task;
import com.rocket.todolist.beam.User;

import java.util.List;

public interface TaskMapper {
    void insertTask(Task task);

    List<Task> selectTasksByUser(User user);

    List<Task> getTasks(Task task);

    void deleteTaskById(int id);

    void updateTask(Task task);
}
