package com.rocket.todolist.controller;

import com.rocket.todolist.beam.User;
import com.rocket.todolist.dao.UserMapper;
import com.rocket.todolist.util.Utils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;
import protobuf.UserProto.*;

@RestController
public class UserController {
    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody LoginResponse userLogin(@RequestBody LoginRequest request) {
        String info = request.getInfo();
        SqlSession sqlSession = Utils.getSqlSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = null;
        if (request.getType() == 1) {
            user = mapper.getUserByEmail(info);
        }
		sqlSession.close();
        LoginResponse.Builder builder = LoginResponse.newBuilder();
        if (user == null) {
            builder.setCode(1001);
            builder.setMessage("user not find");
        } else if (!user.getPwd().equals(request.getPassword())) {
            builder.setCode(1002);
            builder.setMessage("password error");
        } else {
            builder.setId(user.getId());
            builder.setUserName(user.getName());
        }
        return builder.build();
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody DeleteUserResponse userDelete(@RequestBody DeleteUserRequest request) {
        int id = request.getId();
        SqlSession sqlSession = Utils.getSqlSession();
        int res = sqlSession.delete("deleteUserById", id);
        sqlSession.commit();
        sqlSession.close();
        DeleteUserResponse.Builder builder = DeleteUserResponse.newBuilder();
        if (res == 1) {
            builder.setCode(0);
        } else {
            builder.setCode(2);
        }
        return builder.build();
    }
}
