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
    public Map<String,String> login(String email,String password){
        User user = userService.findByEmail(email);
        if(user!=null){
            if(password.equals(user.getPassword())){
                return (Map<String, String>) new HashMap<>().put("msg","登陆成功!");
            }
            else {
                Map<String,String> map = new HashMap<>();
                map.put("msg",JSON.toJSONString("密码或邮箱不正确"));
                return map;
            }
        }
        return (Map<String, String>) new HashMap<>().put("msg","密码或邮箱不正确!");
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
