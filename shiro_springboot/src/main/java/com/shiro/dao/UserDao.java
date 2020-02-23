package com.shiro.dao;

import com.shiro.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户数据访问接口
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    //根据手机号来获取用户的消息
    User findByUsername(String name);
}
