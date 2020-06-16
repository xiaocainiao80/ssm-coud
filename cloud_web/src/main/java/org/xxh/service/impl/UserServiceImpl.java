package org.xxh.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xxh.dao.UserRepository;
import org.xxh.pojo.User;
import org.xxh.service.UserService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public User addUser(User user) {
        int insert = userRepository.insert(user);
        if(insert >0 )
            return user;
        else
            return null;
    }

    public User findByEmail(String email) {
        Example example = Example.builder(User.class)
                .where(Sqls.custom().andEqualTo("email",email))
                .build();
        return userRepository.selectOneByExample(example);
    }
}
