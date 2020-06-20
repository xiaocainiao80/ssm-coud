package org.xxh.example;

import org.xxh.pojo.User;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

public class UserExample {

    public static Example findByEmail(String email){
        return Example.builder(User.class)
                .where(Sqls.custom().andEqualTo("email",email))
                .build();
    }

    public static Example findByUsername(String username){
        return Example.builder(User.class)
                .where(Sqls.custom().andEqualTo("username",username))
                .build();
    }
}
