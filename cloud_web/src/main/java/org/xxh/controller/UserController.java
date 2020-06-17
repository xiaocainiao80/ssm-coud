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

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(){
        return "register";
    }
//    新增用户
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody User resources){
        if(userService.findByEmail(resources.getEmail())!=null){
            return new ResponseEntity<Object>("该用户已经注册!", HttpStatus.BAD_REQUEST);
        }
        resources.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<Object>(userService.addUser(resources), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody User user1){
        User user = userService.findByEmail(user1.getEmail());
        if(user!=null){
            if(user1.getPassword().equals(user.getPassword())){
                return new ResponseEntity<Object>("登陆成功!",HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<Object>("邮箱或密码错误!",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>("该用户不存在!",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
