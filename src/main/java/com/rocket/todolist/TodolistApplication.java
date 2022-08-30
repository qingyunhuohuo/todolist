package com.rocket.todolist;

import com.rocket.todolist.beam.Assignment;
import com.rocket.todolist.beam.User;
import com.rocket.todolist.dao.AssignmentMapper;
import com.rocket.todolist.dao.UserMapper;
import com.rocket.todolist.util.Utils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
@RestController
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

	@GetMapping("/hello1")
	public String hello1(@RequestParam(value = "name", defaultValue = "World") String name) {
		write(new User(name, "123456"));
		return String.format("Hello %s!", name);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		print();
		return String.format("Hello %s!", name);
	}

	private void print() {
		SqlSession sqlSession = Utils.getSqlSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = mapper.getUserList();
		for (User user : users) {
			System.out.println(user);
		}
		sqlSession.close();
	}

	private void write(User user) {
		SqlSession sqlSession = Utils.getSqlSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		mapper.insertUser(user);
		sqlSession.commit();
		sqlSession.close();
	}

	@GetMapping("/test1")
	public String test1(@RequestParam(value = "title", defaultValue = "World") String title, @RequestParam(value = "userId", defaultValue = "1") String userId) {
		writeAssignment(new Assignment(title, new Date(1661358228950L), Integer.parseInt(userId), "待完成"));
		return String.format("Hello %s!", userId);
	}

	@GetMapping("/test")
	public String test(@RequestParam(value = "userId", defaultValue = "World") String userId) {
		List<Assignment> assignments = printAssignment(new User(Integer.parseInt(userId), "xxx", "123456", "xxx"));
		StringBuilder res = new StringBuilder();
		for (Assignment assignment : assignments) {
			res.append(assignment.toString() + "\n");
		}
		return String.format("Hello %s!", res);
	}

	@GetMapping("/testdelete")
	public String testDelete(@RequestParam(value = "id", defaultValue = "World") String id) {
		deleteAssignment(Integer.parseInt(id));
		return String.format("Hello !");
	}

	private void deleteAssignment(int id) {
		SqlSession sqlSession = Utils.getSqlSession();
		AssignmentMapper mapper = sqlSession.getMapper(AssignmentMapper.class);
		mapper.deleteAssignmentById(id);
		sqlSession.commit();
		sqlSession.close();
	}

	@GetMapping("/testupdate")
	public String testUpdate(@RequestParam(value = "id", defaultValue = "World") String id) {
		updateAssignment(Integer.parseInt(id));
		return String.format("Hello !");
	}

	private void updateAssignment(int id) {
		SqlSession sqlSession = Utils.getSqlSession();
		AssignmentMapper mapper = sqlSession.getMapper(AssignmentMapper.class);
		mapper.updateAssignmentById(new Assignment(id, "测试标题", new Date(1661358228950L), "测试分类", "测试内容", "待完成"));
		sqlSession.commit();
		sqlSession.close();
	}

	private List<Assignment> printAssignment(User user) {
		SqlSession sqlSession = Utils.getSqlSession();
		AssignmentMapper mapper = sqlSession.getMapper(AssignmentMapper.class);
		List<Assignment> assignments = mapper.selectAssignmentsByUser(user);
		for (Assignment assignment : assignments) {
			System.out.println(assignment);
		}
		sqlSession.close();
		return assignments;
	}

	private void writeAssignment(Assignment assignment) {
		SqlSession sqlSession = Utils.getSqlSession();
		AssignmentMapper mapper = sqlSession.getMapper(AssignmentMapper.class);
		mapper.insertAssignment(assignment);
		sqlSession.commit();
		sqlSession.close();
	}

}
