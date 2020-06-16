package org.xxh.dao;

import org.springframework.stereotype.Repository;
import org.xxh.pojo.User;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRepository extends Mapper<User> {
}
