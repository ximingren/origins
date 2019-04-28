package com.D5.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 将数据写入response缓存区
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response,Object object) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(object);
        out.flush();
        out.close();
    }
}
