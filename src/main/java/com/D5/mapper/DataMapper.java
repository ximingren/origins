package com.D5.mapper;

import com.D5.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作数据表的数据
 */
@Repository
public interface DataMapper {
    List<Data> selectData(Page page);

    List<Data> selectAllData();

    int selectDataCount();

    int setThreshold(Threshold threshold);

    List<Threshold> selectThreshold();

    Data selectOneData();

    List<Data> selectSpecityData(@Param(value = "time") String time);
}
