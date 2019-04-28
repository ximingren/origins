package com.D5.monitor;

import com.D5.cache.RedisCache;
import com.D5.domain.Addition;
import com.D5.domain.Data;
import com.D5.domain.Threshold;
import com.D5.util.SerializeUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据阈值和实际的数据来判断数据是否正常的类
 */
public class MonitorData {
    public static List<Addition> setAddition(List<Threshold> list, List<Data> datas) {
        List<Addition> additions = new ArrayList<Addition>();
        Addition additionTem = new Addition();
        additionTem.setName("水温");
        Addition additionPH = new Addition();
        additionPH.setName("PH");
        Addition additionDO = new Addition();
        additionDO.setName("溶解氧");
        Addition additionNH = new Addition();
        additionNH.setName("氮氧浓度");
        Addition additionSalt = new Addition();
        additionSalt.setName("含盐量");
        Addition additionCOND = new Addition();
        additionCOND.setName("电导率");
        for (Data e : datas) {
            if ((Double.parseDouble(e.getTem()) > (list.get(0).getUplimit())) || (Double.parseDouble(e.getTem()) < (list.get(0).getLowlimit()))) {
                additionTem.setAddition("异常");
            }
            if ((Double.parseDouble(e.getPH()) > (list.get(1).getUplimit()) || (Double.parseDouble(e.getPH()) < (list.get(1).getLowlimit())))) {
                additionPH.setAddition("异常");
            }
            if ((Double.parseDouble(e.getDO()) > (list.get(2).getUplimit())) || (Double.parseDouble(e.getDO()) < (list.get(2).getLowlimit()))) {
                additionDO.setAddition("异常");
            }
            additionNH.setAddition("异常");
            additionSalt.setAddition("异常");
            if ((Double.parseDouble(e.getCOND()) > (list.get(5).getUplimit())) || (Double.parseDouble(e.getCOND()) > (list.get(5).getLowlimit()))) {
                additionCOND.setAddition("异常");
            }
        }
        additions.add(additionTem);
        additions.add(additionPH);
        additions.add(additionDO);
        additions.add(additionNH);
        additions.add(additionSalt);
        additions.add(additionCOND);
        return additions;
    }//当数据为空的时候，设置为异常。当数据的值小于下限时，设置为异常。当数据的值大于上限时，设置为正常
    //六种数据，六种状态，依次加到list集合中
}
