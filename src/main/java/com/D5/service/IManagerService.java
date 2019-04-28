package com.D5.service;

import com.D5.domain.Role;
import com.D5.domain.User;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理Service类
 */
public interface IManagerService {
    User login(HttpServletRequest request, HttpServletResponse response);//登录

    User selectUser(String mailName);//获取用户信息

    JSONObject addNewUser(HttpServletRequest request,HttpServletResponse response);//添加新用户

    JSONObject change(HttpServletRequest request);//更改用户个人信息

    JSONObject adjust(HttpServletRequest request,HttpServletResponse response);//调整职位

    String selectRoles_id(HttpServletRequest request);//获取用户所属的角色id

    String findUser(String mailName);//查找用户

    Role selectRole(int roles_id);//根据角色id来查找用户所属的角色

    JSONObject forget(HttpServletRequest request);//忘记密码
}
