package com.rocket.todolist.controller;

import com.rocket.todolist.beam.Task;
import com.rocket.todolist.protobuf.TaskProto;
import com.rocket.todolist.protobuf.TaskProto.*;
import com.rocket.todolist.util.Utils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class TaskController {
    @RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody AddTaskResponse addTask(@RequestBody AddTaskRequest request) {
        Task task = convertTaskToJava(request.getTask());
        SqlSession sqlSession = Utils.getSqlSession();
        int res = sqlSession.insert("insertTask", task);
        sqlSession.commit();
        sqlSession.close();
        AddTaskResponse.Builder builder = AddTaskResponse.newBuilder();
        if (res == 1) {
            builder.setResponse(getResponse(ResponseCode.Success, "add task succeed"));
        } else {
            builder.setResponse(getResponse(ResponseCode.AddTaskError, "add task error"));
        }
        return builder.build();
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody DeleteTaskResponse deleteTask(@RequestBody DeleteTaskRequest request) {
        SqlSession sqlSession = Utils.getSqlSession();
        int res = sqlSession.delete("deleteTaskById", request.getId());
        sqlSession.commit();
        sqlSession.close();
        DeleteTaskResponse.Builder builder = DeleteTaskResponse.newBuilder();
        if (res == 1) {
            builder.setResponse(getResponse(ResponseCode.Success, "delete task succeed"));
        } else {
            builder.setResponse(getResponse(ResponseCode.DeleteTaskError, "delete task error"));
        }
        return builder.build();
    }

    @RequestMapping(value = "/updateTask", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody UpdateTaskResponse updateTask(@RequestBody UpdateTaskRequest request) {
        SqlSession sqlSession = Utils.getSqlSession();
        int res = sqlSession.update("updateTask", convertTaskToJava(request.getTask()));
        sqlSession.commit();
        sqlSession.close();
        UpdateTaskResponse.Builder builder = UpdateTaskResponse.newBuilder();
        if (res == 1) {
            builder.setResponse(getResponse(ResponseCode.Success, "update task succeed"));
        } else {
            builder.setResponse(getResponse(ResponseCode.UpdateTaskError, "update task error"));
        }
        return builder.build();
    }

    @RequestMapping(value = "/getTask", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody GetTasksResponse getTask(@RequestBody GetTasksRequest request) {
        SqlSession sqlSession = Utils.getSqlSession();
        List<Task> tasks = sqlSession.selectList("getTasks", new Task(request.getUserId(),
                new Date(request.getStartTime()), new Date(request.getEndTime()), request.getCategory(), request.getState()));
        sqlSession.close();
        GetTasksResponse.Builder builder = GetTasksResponse.newBuilder();
        builder.setResponse(getResponse(ResponseCode.Success, "get tasks succeed"));
        for (int i = 0; i < tasks.size(); i++) {
            builder.setTasks(i, convertTaskToProto(tasks.get(i)));
        }
        return builder.build();
    }

    private static Task convertTaskToJava(TaskProto.Task task) {
        return new Task(task.getId(), task.getUserId(), task.getTitle(),
                new Date(task.getStartTime()), new Date(task.getEndTime()), task.getColor(),
                task.getCategory(), task.getContent(), task.getState(),
                task.getRepeat(), task.getRemindMethod(), new Date(task.getRemindTime()),
                task.getAddress());
    }

    private static TaskProto.Task convertTaskToProto(Task task) {
        TaskProto.Task.Builder builder = TaskProto.Task.newBuilder();
        builder.setId(task.getId());
        builder.setUserId(task.getUserId());
        builder.setTitle(task.getTitle());
        builder.setStartTime(task.getStartTime().getTime());
        builder.setEndTime(task.getEndTime().getTime());
        builder.setColor(task.getColor());
        builder.setCategory(task.getCategory());
        builder.setContent(task.getContent());
        builder.setState(task.getState());
        builder.setRepeat(task.getRepeat());
        builder.setRemindMethod(task.getRemindMethod());
        builder.setRemindTime(task.getRemindTime().getTime());
        builder.setAddress(task.getAddress());
        return builder.build();
    }

    private static Response getResponse(ResponseCode code, String message) {
        Response.Builder builder = Response.newBuilder();
        builder.setCode(code);
        builder.setMessage(message);
        return builder.build();
    }
}
