package com.rocket.todolist.dao;

import com.rocket.todolist.beam.Assignment;
import com.rocket.todolist.beam.User;

import java.util.List;

public interface AssignmentMapper {
    void insertAssignment(Assignment assignment);

    List<Assignment> selectAssignmentsByUser(User user);

    void deleteAssignmentById(int id);

    void updateAssignmentById(Assignment assignment);
}
