package com.D5.controller;

import com.D5.cache.RedisCache;
import com.D5.domain.*;
import com.D5.monitor.Monitor;
import com.D5.monitor.MonitorData;
import com.D5.service.impl.DataService;
import com.D5.util.ResponseUtil;
import com.D5.util.SerializeUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 数据Controller
 */
@Controller
public class DataController {
    @Autowired
    private DataService dataService;
    private Map<String, Object> map = new HashMap<String, Object>();//存放数据
    public static List<Threshold> thresholds;
    public static List<Addition> additions;
    private Logger logger = Logger.getLogger("DataController");//获取日志类

    /**
     * 最新数据
     *
     * @param request
     * @return ajax所需的数据
     */
    @ResponseBody
    @RequestMapping(value = "/latestData", method = RequestMethod.POST)
    public String latest(HttpServletRequest request) throws Exception {
        Page page = new Page();
        page.setCount(dataService.selectDataCount());//设置水池总数
        page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));//设置当前页
        List<Data> data = dataService.selectData(page,request);//获取每一个水池的最新数据
        map.put("data", data);//水池数据
        map.put("page", page);//分页类
        return new JSONObject(map).toString();
    }


    /**
     * 设置阈值
     *
     * @param request
     * @return Modelandview
     */
    @RequestMapping(value = "/monitor")
    public void monitor(HttpServletRequest request,  HttpServletResponse response) throws IOException {
        JSONObject jsonObject = dataService.setThreshold(request,response);//返回值中包含操作的结果
        ResponseUtil.write(response, jsonObject);//写入response缓存区
    }

    /**
     * 获取设备工作状态
     *
     * @return Modelandview
     */
    @RequestMapping(value = "/getMonitor")
    public ModelAndView getMonitor() {
        thresholds = dataService.selectThreshold();//获取阈值
        map.put("threshold", thresholds);//阈值
        map.put("additions", additions);//工作状态
        return new ModelAndView("monitor", map);
    }

    /**
     * 获得所有数据并返回图表页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/chart")
    public String chart(Model model, @RequestParam("scope") String scope) {
        List<Data> list = null;
        //当传过来的参数中包含"-"的时候，则说明要求查找特定日期的数据
        if (scope.indexOf("-") != -1) {
            list = dataService.selectDayData(scope);
            model.addAttribute("scope", scope);
        }
        //当参数等于“今天”时，返回当天最新数据
        if (scope.equals("today")) {
            list = dataService.selectTodayData();
            model.addAttribute("scope", "今天");
        }
        //返回一周之内的数据
        if (scope.equals("week")) {
            list = dataService.selectWeekData();
            model.addAttribute("scope", "近周");
        }
        // 返回全部数据
        if (scope.equals("all")) {
            list = dataService.selectAllData();//查找所有的数据
            model.addAttribute("scope", "全部");
        }
        //如果找不到数据，设置一个变量值，让页面知道出现了错误：找不到数据
        if ( list.isEmpty()) {
            model.addAttribute("error", "找不到数据");
        }
        model.addAttribute("list", list);//将数据集合list放入页面中
        return "chart";
    }

    /**
     * 预警监控的开启与关闭
     *
     * @param open
     * @param request
     * @return 重定向到预警监控页面
     */
    @RequestMapping("/openMonitor")
    public String setMonitor(@RequestParam("open") String open, HttpServletRequest request) {
        // 如果传过来的参数等于true的话，则说明要求开启预警监控
        if (open.equals("true")) {
            Monitor.setFlag(true);//开启定时器的任务
            DataController.additions = MonitorData.setAddition(thresholds, dataService.selectAllData());//手动设置工作状态
            request.getSession().setAttribute("open", "true");//根据要求，预警监控的状态变量是服务器级别的
        }
        // 参数不等于true，则为关闭预警监控
        else {
            Monitor.setFlag(false);//关闭定时器的任务
            DataController.additions = null;//关闭预警监控后，清空设备工作状态的变量
            request.getSession().setAttribute("open", "false");
        }
        return "redirect:getMonitor";//返回预警监控页面
    }

}
