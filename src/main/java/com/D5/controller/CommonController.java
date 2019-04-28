package com.D5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @date:
 * @version:
 * @description：仅仅获取页面，没操作
 */
@Controller
public class CommonController {
    @RequestMapping("/")
    public String getLogin() {
        return "login";
    }

    @RequestMapping("/getAddUser")
    public String addser() {
        return "addUser";
    }

    @RequestMapping("/getAdjust")
    public String adjust() {
        return "adjust";
    }

    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/getForget")
    public String forget() {
        return "forget";
    }

    @RequestMapping("/four")
    public String errorFour() {
        return "index";
    }

    @RequestMapping("/five")
    public String errorFive() {
        return "index";
    }
}
