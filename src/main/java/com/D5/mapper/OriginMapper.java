package com.D5.mapper;

import com.D5.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OriginMapper {
    List<Data> selectData(Page page);
    List<Data> selectAllData();
    int selectDataCount();
    User login(User user);
    int addUser(User user);
    User selectUser(User user);
    void deleteUser(String account);
    int change(User user);
    String selectRoles_id(String account);
    void deleteThreshold();
    int setThreshold(Threshold threshold);
    List<Threshold> selectThreshold();
    Data selectOneData();
}
