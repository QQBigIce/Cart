package com.how2java.orderItem;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OrderItemList")
public class OrderItemList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        list(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<OrderItem> list = (List<OrderItem>) request.getSession().getAttribute("ois");
        if (null == list)
            return;
        String jsonString = JSONObject.toJSONString(list);
        System.out.println(jsonString);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(jsonString);
        pw.close();
    }
}
