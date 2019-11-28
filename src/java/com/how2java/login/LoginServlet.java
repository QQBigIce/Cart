package com.how2java.login;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("user");
        User user = JSONObject.parseObject(name, User.class);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        List<User> list = new UserDAO().get(user.getName());
        for (User user1 : list) {
            if (user1.getName().equals(user.getName()) && (user1.getPassword().equals(user.getPassword()))){
                HttpSession session = request.getSession();
                session.setAttribute("name", user.getName());
                session.setAttribute("password", user.getPassword());

                pw.print("登录成功，欢迎您" + user.getName());
                pw.close();
                return;
            }
        }
        pw.print("未找到用户");
        pw.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
