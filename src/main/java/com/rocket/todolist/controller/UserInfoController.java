package com.rocket.todolist.controller;

import com.rocket.todolist.beam.User;
import com.rocket.todolist.dao.UserMapper;
import com.rocket.todolist.protobuf.UserLogin;
import com.rocket.todolist.util.Utils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {
    @RequestMapping(value="/hello", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody UserLogin.UserInfoResponse getUserInfo(@RequestBody UserLogin.UserInfoRequest request) throws Exception {
        int id = (int) request.getUserId();
        SqlSession sqlSession = Utils.getSqlSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.getUserById(id);
        System.out.println(user);
		sqlSession.close();
        UserLogin.UserInfoResponse.Builder builder = UserLogin.UserInfoResponse.newBuilder();
        builder.setMobile(user.getEmail());
        builder.setUserType(user.getId());
        return builder.build();
    }
}
