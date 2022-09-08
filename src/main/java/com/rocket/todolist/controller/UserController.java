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
        boolean isSucceed = user != null && user.getPwd().equals(request.getPassword());
        builder.setIsSucceed(isSucceed);
        builder.setUserName(isSucceed ? user.getName() : "");
        return builder.build();
    }
}
