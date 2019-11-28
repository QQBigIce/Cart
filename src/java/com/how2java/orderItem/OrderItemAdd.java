package com.how2java.orderItem;

import com.how2java.product.Product;
import com.how2java.product.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderItemAdd")
public class OrderItemAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        add(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response){
        int num = Integer.parseInt(request.getParameter("num"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product p = ProductDAO.getInstance().get(pid);

        OrderItem oi = new OrderItem(p, num);

        List<OrderItem> ois = (List<OrderItem>) request.getSession(false).getAttribute("ois");
        if (null == ois){
            ois = new ArrayList<>();
            request.getSession().setAttribute("ois", ois);
        }
        ois.add(oi);
//        response.sendRedirect();
    }
}
