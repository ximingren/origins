package com.D5.mapper;

import com.D5.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作用户表的数据
 */
@Repository
public interface ManagerMapper {

    User login(@Param(value = "mailName") String mailName, @Param(value = "password") String password);

    int addUser(User user);

    User selectUser(@Param(value = "mailName") String mailName);

    int change(User user);

    String selectRoles_id(@Param(value = "mailName") String mailName);

    Role selectRole(int roles_id);

}
