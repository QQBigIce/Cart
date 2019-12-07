package com.how2java.orderItem;

import com.alibaba.fastjson.JSONObject;
import com.how2java.product.Product;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OrderItemDel")
public class OrderItemDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        del(request, response);
    }

    protected void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = request.getParameter("orderItem");
        OrderItem p = JSONObject.parseObject(s, OrderItem.class);
        System.out.println(p);
        List<OrderItem> ois = (List<OrderItem>) request.getSession(false).getAttribute("ois");
        System.out.println(ois);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if ((null == ois)) {
            out.print("失败");
            out.close();
            return;
        }
        ois.remove(p);
        out.print("成功");
        System.out.println(ois);
        out.close();
    }
}
