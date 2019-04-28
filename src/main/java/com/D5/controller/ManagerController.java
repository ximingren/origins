package com.D5.controller;

import com.D5.cache.RedisCache;
import com.D5.domain.Role;
import com.D5.util.ResponseUtil;
import com.D5.util.SendMail;
import com.D5.domain.User;
import com.D5.service.IManagerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 用户管理Controller
 */
@Controller
public class ManagerController {
    @Autowired
    private IManagerService managerService;
    private User user;
    private static Role role;
    private Map<String, Object> map = new HashMap<String, Object>();
    private Logger logger = Logger.getLogger("ManagerController");

    /**
     * 登录功能
     *
     * @param request
     * @param response
     * @return String
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user = managerService.login(request, response);//根据邮箱名和密码，查找是否存在该用户
        if (user != null) {
            role = (Role) request.getSession().getAttribute("role");//查找该用户所属的角色
            request.getSession().setAttribute("role", role);//设置session变量role
            return "redirect:index";//登录成功后，进入首页
        }//登录成功
        else {
            return "redirect:/";//登录失败，回到登录页面
        }//登录失败
    }

    /**
     * 添加成员
     *
     * @param request
     * @return Modelandview
     */
    @RequestMapping(value = "/addUser")
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //只能添加职位低于操作者的用户
        JSONObject jsonObject = managerService.addNewUser(request, response);//返回值包含操作的结果
        ResponseUtil.write(response, jsonObject);//写入response缓存区
    }

    /**
     * 更改个人信息
     *
     * @param request
     * @return Modelandview
     */
    @Cacheable
    @RequestMapping(value = "/userChange")
    public void userChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = managerService.change(request);//获取操作结果
        ResponseUtil.write(response, jsonObject);//写入response缓存区
    }

    /**
     * 忘记密码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/forget")
    public void forget(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = managerService.forget(request);//获取操作结果
        ResponseUtil.write(response, jsonObject);//写入response缓存区
    }

    /**
     * 获取修改个人信息的页面
     *
     * @return Modelandview
     */

    @RequestMapping("/change")
    public ModelAndView change() {
        user = managerService.selectUser(user.getMailName());//修改个人信息后，要重新获取User变量
        map.put("user", user);
        return new ModelAndView("change", map);
    }

    /**
     * 调整职位
     *
     * @param request
     * @return modelandview
     */
    @RequestMapping("/adjust")
    public void adjust(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = managerService.adjust(request, response);//jsonObject中存放操作结果
        ResponseUtil.write(response, jsonObject);
    }

    /**
     * 发送邮件
     *
     * @param mail
     * @param response
     * @throws Exception
     */
    @RequestMapping("/sendMail")
    public void sendMail(@RequestParam("mail") String mail, HttpServletResponse response, HttpServletRequest request) throws Exception {
        try {
            int indentifyNum = (int) (Math.random() * 999999);//随机获得6位数的验证码
            SendMail sendMail = new SendMail(mail, indentifyNum);//获取邮箱与验证码
            sendMail.send();//发送邮箱
            response.getWriter().print(indentifyNum);//写入response区
        } catch (Exception e) {
            logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + mail + "发送邮箱失败");
            response.getWriter().print("false");//写入response区
        }
    }

    /**
     * 登出功能
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request, Model model) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("role");
        request.getSession().removeAttribute("userMessage");
        request.getSession().setAttribute("newUser", "true");
        //清除所有参数，以及设置新变量让登录页面知道是登出的用户
        return "redirect:/";
    }

    /**
     * 查找用户是否存在
     *
     * @param mailName
     * @return
     */
    @ResponseBody
    @RequestMapping("/findUser")
    public String findUser(@RequestParam("mailName") String mailName) {
        return managerService.findUser(mailName);
    }

}
