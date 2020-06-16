package org.xxh.service;

import org.xxh.pojo.User;

public interface UserService {

//    增加
    User addUser(User user);

//  email查找user
    User findByEmail(String email);


}
