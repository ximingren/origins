package com.D5.service.impl;

import com.D5.cache.RedisCache;
import com.D5.controller.ManagerController;
import com.D5.domain.*;
import com.D5.mapper.DataMapper;
import com.D5.service.IDataService;
import com.D5.util.SerializeUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;


@Service
public class DataService implements IDataService {
    @Autowired
    private DataMapper datamapper;
    @Autowired
    private RedisCache redisCache;
    private Role role;
    private Logger logger = Logger.getLogger("DataService");

    /**
     * 获取数据
     *
     * @param page
     * @return
     */
    public List<Data> selectData(Page page, HttpServletRequest request) throws ParseException {
        List<Data> data = datamapper.selectData(page);
        return data;
    }

    /**
     * 获取全部数据
     *
     * @return
     */
    public List<Data> selectAllData() {
        System.out.println("test");
        List<Data> allData = datamapper.selectAllData();
        return allData;
    }

    /**
     * 获取今天数据
     *
     * @return
     */
    public List<Data> selectTodayData() {
        return datamapper.selectSpecityData("Today");
    }

    /**
     * 获取特定时间的数据
     *
     * @param scope
     * @return
     */
    public List<Data> selectDayData(String scope) {
        return datamapper.selectSpecityData(scope);
    }

    /**
     * 获取一周之内的数据
     *
     * @return
     */
    public List<Data> selectWeekData() {
        return datamapper.selectSpecityData("Week");
    }

    /**
     * 获得水池个数
     *
     * @return
     */
    public int selectDataCount() {
        return datamapper.selectDataCount();
    }

    /**
     * 获取阈值
     *
     * @return 阈值的list集合
     */
    public List<Threshold> selectThreshold() {
        return datamapper.selectThreshold();
    }

    /**
     * 获取最新一条数据
     *
     * @return Data
     */
    public Data selectOneData() {
        return datamapper.selectOneData();
    }

    /**
     * 设置阈值
     *
     * @param request
     */
    public JSONObject setThreshold(HttpServletRequest request, HttpServletResponse response) {
        String[] name = {"水温", "PH", "溶解氧", "氮氧浓度", "含盐量", "电导率"};//用于mybatis
        String[] enName = {"tem", "PH", "DO", "NH", "salt", "COND"};//用于redis
        int option = 0;
        JSONObject jsonObject = new JSONObject();
        role=(Role) request.getSession().getAttribute("role");
        try {
            if (role.getMenu_Id() != null) {
                String[] uplimit = request.getParameterValues("uplimit");//阈值上限
                String[] lowlimit = request.getParameterValues("lowlimit");//阈值下限
                for (int i = 0; i < uplimit.length; i++) {
                    Threshold threshold = new Threshold();
                    threshold.setName(name[i]);
                    threshold.setLowlimit(Double.parseDouble(lowlimit[i]));
                    threshold.setUplimit(Double.parseDouble(uplimit[i]));
                    option = datamapper.setThreshold(threshold);//设置阈值
                }
                List<Threshold> list = datamapper.selectThreshold();//获取阈值
                for (int i = 0; i < uplimit.length; i++) {
                    redisCache.set(enName[i].getBytes(), SerializeUtil.serialize(list.get(i)));//向redis数据库中持久化阈值
                }
                User user = (User) request.getSession().getAttribute("user");//获取用户信息，以便于记录操作
                if (option > 0) {
                    logger.info("ip地址为：" + request.getRemoteAddr() + " 用户：" + user.getMailName() + "重新设置了阈值");//记录操作信息
                    jsonObject.put("success", true);//操作成功
                } else {
                    jsonObject.put("success", false);//操作失败
                }
            }
            if (role.getMenu_Id() == null) {
                request.getSession().setAttribute("userMessage", "您没有权限访问该网页");
                request.getRequestDispatcher("index").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
            jsonObject.put("success", false);//出现异常，则说明操作失败
        } finally {
            return jsonObject;
        }
    }
}