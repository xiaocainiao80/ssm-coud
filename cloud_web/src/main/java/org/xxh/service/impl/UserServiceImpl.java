package org.xxh.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xxh.dao.UserRepository;
import org.xxh.example.UserExample;
import org.xxh.exception.EntityExistException;
import org.xxh.exception.EntityNotFoundException;
import org.xxh.pojo.User;
import org.xxh.service.UserService;
import org.xxh.utils.ValidationUtil;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        if((findByEmail(user.getEmail()))!=null){
            throw new EntityExistException(User.class,"email",user.getEmail());
        }

        if(findByUsername(user.getUsername())!=null){
            throw new EntityExistException(User.class,"username",user.getUsername());
        }
        int insert = userRepository.insert(user);
        if(insert >0 )
            return user;
        else
            return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.selectOneByExample(UserExample.findByEmail(email));
    }

    @Override
    public User findByUsername(String username){
        return userRepository.selectOneByExample(UserExample.findByUsername(username));
    }

    @Override
    public User findByNameOrEmail(String username) {
        User user;
        String flag = null;
        if(ValidationUtil.isEmail(username)){
            flag = "email";
            user = findByEmail(username);
        } else {
            flag = "userName";
            user = findByUsername(username);
        }
        if (user == null) {
            throw new EntityNotFoundException(User.class, flag, username);
        } else {
            return user;
        }
    }
}
