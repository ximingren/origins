package com.D5.monitor;

import com.D5.cache.RedisCache;
import com.D5.controller.DataController;
import com.D5.domain.Addition;
import com.D5.service.impl.DataService;
import com.D5.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import static com.D5.controller.DataController.additions;

/**
 * 预警监控的类
 */
@Component
public class Monitor {
    @Autowired
    private DataService dataService;
    private static boolean flag = false;//是否预警监控
    private static RedisCache redisCache = (RedisCache) ContextLoader.getCurrentWebApplicationContext().getBean("redisCache");

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        Monitor.flag = flag;
    }

    @Scheduled(cron = "0/5 * *  * * ?")//每5秒执行一次monitor方法
    public void monitor() {
        if (flag == true) {
            additions = MonitorData.setAddition(DataController.thresholds, dataService.selectAllData());//设置工作状态
            additions = additions;//赋值
        }
        if (flag == false) {
            additions = null;//当不监控时，设置工作状态为null
        }
    }
}