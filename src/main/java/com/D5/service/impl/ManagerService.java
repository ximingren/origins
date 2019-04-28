package com.D5.service.impl;

import com.D5.cache.RedisCache;
import com.D5.domain.Role;
import com.D5.domain.User;
import com.D5.mapper.ManagerMapper;
import com.D5.service.IManagerService;
import com.D5.util.SerializeUtil;
import com.D5.util.WebUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Service
public class ManagerService implements IManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private RedisCache redisCache;
    private Logger logger = Logger.getLogger("ManagerService");
    private User u;//当前用户
    private Role role;

    /**
     * 登录
     *
     * @param request
     * @param response
     * @return
     */
    @Transactional
    public User login(HttpServletRequest request, HttpServletResponse response) {
        try {
            //如果勾选了记住密码的复选框，则添加邮箱密码进入cookie
            if ("true".equals(request.getParameter("rememberMe"))) {
                String loginInfo = request.getParameter("mailName") + "#" + request.getParameter("password");
                Cookie cookie = new Cookie("loginInfo", loginInfo);
                cookie.setMaxAge(30 * 24 * 60 * 60);   //存活期为一个月 30*24*60*60
                cookie.setPath("/");
                response.addCookie(cookie);
            }//添加cookie
            u = managerMapper.login(request.getParameter("mailName"), request.getParameter("password"));//根据邮箱名和密码，查找是否存在该用户
            if (u != null) {
                role = managerMapper.selectRole(u.getRoles_id());
                request.getSession().setAttribute("role", role);
                logger.info("ip地址为：" + request.getRemoteAddr() + "  用户：" + request.getParameter("mailName") + "登录成功");//记录信息
                request.getSession().setAttribute("user", u);//设置user的session变量
                return u;
            }//登录成功
            else {
                request.getSession().setAttribute("userMessage", "账号或密码错误");//当user为空时，说明找不到这样的用户，因此是账号密码错误
                return null;
            }//登录失败
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("userMessage", "账号或密码中不能有空格");
            return null;
        } //当抛出异常时，说明是用户填写的账号密码中有空哥，而空格是不能记录在cookie里面的
    }

    /**
     * 添加新成员
     *
     * @param request
     * @return
     */
    @Transactional
    public JSONObject addNewUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        User user = WebUtil.fillBean(request, User.class);//动态设置实体类的值
        try {
            if (role.getMenu_Id() != null) {
                int flag = managerMapper.addUser(user);//添加用户
                redisCache.set(user.getMailName().getBytes(), SerializeUtil.serialize(managerMapper.selectUser(user.getMailName())));//添加新用户到redis数据库
                if (flag == 1) {
                    jsonObject.put("success", true);
                    logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + u.getMailName() + "添加新用户" + user.getMailName() + "成功");
                }//如果managerMapper执行后返回值为1，则说明执行成功
                else {
                    jsonObject.put("success", false);
                    logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + u.getMailName() + "添加新用户" + user.getMailName() + "失败");
                }//如果返回值不等于1，则说明操作失败
            }
            if (role.getMenu_Id() == null) {
                request.getSession().setAttribute("userMessage", "您没有权限访问该网页");
                request.getRequestDispatcher("index").forward(request, response);
            }//阻止不够权限的用户通过url访问网页
        } catch (Exception e) {
            jsonObject.put("success", false);
            logger.info("添加新用户时出现异常  "+e.getMessage());
        }//抛出异常，也是操作失败
        finally {
            return jsonObject;
        }
    }

    /**
     * 修改个人信息
     *
     * @param request
     * @return
     */
    @Transactional
    public JSONObject change(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        User user = WebUtil.fillBean(request, User.class);//动态设置实体类的值
        user.setRoles_id(Integer.parseInt(managerMapper.selectRoles_id(request.getParameter("mailName"))));
        try {
            int flag = managerMapper.change(user);
            redisCache.set(user.getMailName().getBytes(), SerializeUtil.serialize(managerMapper.selectUser(user.getMailName())));//将修改
            if (flag > 0) {
                jsonObject.put("success", true);
            } else {
                jsonObject.put("success", false);
            }
            //日志记录
            if (flag == 1) {
                if (u != null) {
                    logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + u.getMailName() + "修改" + user.getMailName() + "信息成功");
                } //当u（代表用户）为空，则说明用户尚未登录，则是修改跟人信息
                else {
                    logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + user.getMailName() + "重置密码成功");
                }
            }//如果managerMapper执行后返回值为1，则说明执行成功
            else {
                if (u != null) {
                    logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + u.getMailName() + "修改" + user.getMailName() + "信息失败");
                } else {
                    logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + user.getMailName() + "重置密码成功");
                }
            }
        } catch (Exception e) {
            jsonObject.put("success", false);
            logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + user.getMailName() + "改变个人信息时出现异常 "+e.getMessage());
        } finally {
            return jsonObject;
        }
    }


    /**
     * 调整职位
     *
     * @param request
     * @return
     */
    public JSONObject adjust(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        User user = WebUtil.fillBean(request, User.class);//动态设置实体类的值
        try {
            if (role.getMenu_Id() != null) {
                String accountRoles_id = selectRoles_id(request);//被修改用户原来的职位
                if (Integer.parseInt(accountRoles_id) > u.getRoles_id()) {
                    int flag = managerMapper.change(user);//需要邮箱，角色的变量
                    redisCache.set(user.getMailName().getBytes(), SerializeUtil.serialize(managerMapper.selectUser(user.getMailName())));
                    if (flag > 0) {
                        jsonObject.put("success", true);//操作成功
                    } else {
                        jsonObject.put("success", false);//操作失败
                    }
                } //操作者只能修改职位低于本人的用户，且自己的职位比要更改的职位高
                else {
                    jsonObject.put("success", "error");
                }
            }
            if (role.getMenu_Id() == null) {
                request.getSession().setAttribute("userMessage", "您没有权限访问该网页");
                request.getRequestDispatcher("index").forward(request, response);
            }//阻止不够权限的用户通过url访问网页
        } catch (Exception e) {
            jsonObject.put("success", false);
            logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + u.getMailName() + "调整" + user.getMailName() + "职位时出现异常 "+e.getMessage());
        } finally {
            return jsonObject;
        }
    }

    /**
     * 查找用户是否存在
     *
     * @param mailName
     * @return
     */
    public String findUser(String mailName) {
        if (managerMapper.selectUser(mailName) == null) {
            return "false";
        } else {
            return "true";
        }
    }

    /**
     * 获取被调整用户的职位
     *
     * @param request
     * @return
     */
    public String selectRoles_id(HttpServletRequest request) {
        return managerMapper.selectRoles_id(request.getParameter("mailName"));
    }

    /**
     * 获取当前的用户数据
     *
     * @param mailName
     * @return
     */
    @Cacheable(value = "myCache",key = "#mailName")
    public User selectUser(String mailName) {
        return managerMapper.selectUser(mailName);
    }


    /**
     * 查找用户所属的角色
     *
     * @param roles_id
     * @return role
     */
    public Role selectRole(int roles_id) {
        return managerMapper.selectRole(roles_id);
    }

    /**
     * 忘记密码
     *
     * @param request
     * @return
     */
    public JSONObject forget(HttpServletRequest request) {
        return change(request);//因为个人信息修改和忘记密码的操作实际上是一样的，所以可以重复利用
    }
}
