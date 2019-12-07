package com.how2java.order;

import com.how2java.login.User;
import com.how2java.orderItem.OrderItem;
import com.how2java.orderItem.OrderItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        create(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        Order o = new Order(user);

        OrderDao.getInstance().add(o);
        List<OrderItem> ois = (List<OrderItem>) request.getSession().getAttribute("ois");
        for (OrderItem oi : ois) {
            oi.setOrder(o);
            new OrderItemDao().add(oi);
        }
        ois.clear();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("订单创建成功");
    }
}
