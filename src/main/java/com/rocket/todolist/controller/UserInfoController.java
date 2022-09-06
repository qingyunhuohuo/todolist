package com.rocket.todolist.controller;

import com.rocket.todolist.protobuf.UserLogin;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {
    @RequestMapping(value="/hello", method = RequestMethod.GET, produces = "application/x-protobuf")
    @CrossOrigin
    public @ResponseBody UserLogin.UserInfoResponse getUserInfo(@RequestBody UserLogin.UserInfoRequest request) throws Exception {
        UserLogin.UserInfoResponse.Builder builder = UserLogin.UserInfoResponse.newBuilder();
        builder.setMobile("123432423");
        builder.setUserType(1122);
        return builder.build();
    }
}
