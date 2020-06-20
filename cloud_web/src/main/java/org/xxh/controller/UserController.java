package org.xxh.controller;


import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xxh.pojo.User;
import org.xxh.service.UserService;
import org.xxh.utils.JwtUtil;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


//    新增用户
    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody User resources){
        resources.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<Object>(userService.addUser(resources), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user1) throws Exception {
//        System.out.println(user1.toString());
        User user = userService.findByNameOrEmail(user1.getEmail());
//        user不存在在service层已解决
        if(user.getPassword().equals(user1.getPassword())){
            //给用户jwt加密生成token
            Map<String,Object> map = new HashMap<>();
            String token = "Bearer "+JwtUtil.createJwt(String.valueOf(user.getUserId()),user.getUsername());
            map.put("token",token);

            return new ResponseEntity<Object>(map,HttpStatus.OK);
        }else {
            return new ResponseEntity<Object>("用户名或密码错误!",HttpStatus.BAD_REQUEST);
        }
    }

}
