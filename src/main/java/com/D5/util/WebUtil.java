package com.D5.util;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 将表单的数据动态赋值POJO类
 */
public class WebUtil {
	public static <T>T fillBean(HttpServletRequest request,Class<T> clazz){
		try {
			T t = clazz.newInstance();
			BeanUtils.populate(t, request.getParameterMap());
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
