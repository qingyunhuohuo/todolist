package com.rocket.todolist.controller;

import com.rocket.todolist.beam.User;
import com.rocket.todolist.protobuf.Common;
import com.rocket.todolist.protobuf.UserProto;
import com.rocket.todolist.protobuf.UserProto.*;
import com.rocket.todolist.security.config.JwtSecurityProperties;
import com.rocket.todolist.security.util.JwtTokenUtils;
import com.rocket.todolist.util.Utils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private JwtSecurityProperties jwtSecurityProperties;
    private final JwtTokenUtils jwtTokenUtils = new JwtTokenUtils(jwtSecurityProperties);
    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody UserLoginResponse userLogin(@RequestBody UserLoginRequest request) {
        SqlSession sqlSession = Utils.getSqlSession();
        User user = null;
        switch (request.getType()) {
            case Email :
                user = sqlSession.selectOne("userLoginByEmail", convertUserToJava(request.getUser()));
                break;
            default:
                break;
        }
        sqlSession.close();
        UserLoginResponse.Builder builder = UserLoginResponse.newBuilder();
        if (user == null) {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.UserLoginError, "login error"));
        } else {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.Success, "login error"));
            builder.setUser(convertUserToProto(user));
            Map map = new HashMap();
            map.put("user", user.getEmail());
            map.put("password", user.getPwd());
            builder.setToken(jwtTokenUtils.createToken("log", map));
        }

        return builder.build();
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody DeleteUserResponse deleteUser(@RequestBody DeleteUserRequest request) {
        SqlSession sqlSession = Utils.getSqlSession();
        int res = sqlSession.delete("deleteUserById", request.getId());
        sqlSession.commit();
        sqlSession.close();
        DeleteUserResponse.Builder builder = DeleteUserResponse.newBuilder();
        if (res == 1) {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.Success, "delete user succeed"));
        } else {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.DeleteTUserError, "user task error"));
        }
        return builder.build();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody AddUserResponse addUser(@RequestBody AddUserRequest request) {
        SqlSession sqlSession = Utils.getSqlSession();
        int res = sqlSession.insert("insertUser", convertUserToJava(request.getUser()));
        sqlSession.commit();
        sqlSession.close();
        AddUserResponse.Builder builder = AddUserResponse.newBuilder();
        if (res == 1) {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.Success, "add user succeed"));
        } else {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.AddUserError, "add task error"));
        }
        return builder.build();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody UpdateUserResponse updateUser(@RequestBody UpdateUserRequest request) {
        SqlSession sqlSession = Utils.getSqlSession();
        int res = sqlSession.update("updateUser", convertUserToJava(request.getUser()));
        sqlSession.commit();
        sqlSession.close();
        UpdateUserResponse.Builder builder = UpdateUserResponse.newBuilder();
        if (res == 1) {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.Success, "update user succeed"));
        } else {
            builder.setResponse(Utils.getResponse(Common.ResponseCode.UpdateUserError, "update task error"));
        }
        return builder.build();
    }

    private static User convertUserToJava(UserProto.User user) {
        return new User(user.getId(), user.getName(), user.getPwd(), user.getEmail());
    }

    private static UserProto.User convertUserToProto(User user) {
        UserProto.User.Builder builder = UserProto.User.newBuilder();
        builder.setId(user.getId());
        builder.setName(user.getName());
        builder.setPwd(user.getPwd());
        builder.setEmail(user.getEmail());
        return builder.build();
    }
}
