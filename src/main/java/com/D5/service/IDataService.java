package com.D5.service;

import com.D5.domain.Data;
import com.D5.domain.Page;
import com.D5.domain.Threshold;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * 数据操作Service
 */
public interface IDataService {

    List<Data> selectData(Page page,HttpServletRequest request) throws ParseException;//分水池查找最新数据

    List<Threshold> selectThreshold();//获取阈值

    int selectDataCount();//获取水池总数

    JSONObject setThreshold(HttpServletRequest request, HttpServletResponse response);//设置阈值

    Data selectOneData();//获取最新一条数据
    @Cacheable(value = "myCache")
    List<Data> selectAllData();
}
