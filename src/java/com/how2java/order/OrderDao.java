package com.how2java.order;

import com.how2java.DaoPackage.Dao;
import com.how2java.login.User;
import com.how2java.login.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends Dao {

    private static OrderDao orderDao;

    public static OrderDao getInstance(){
        if (orderDao == null){
            synchronized (OrderDao.class){
                if (orderDao == null){
                    orderDao = new OrderDao();
                }
            }
        }
        return orderDao;
    }

    private OrderDao() {
        super();
        this.table = "order_";
    }

    @Override
    public List<Order> result(ResultSet rs) throws SQLException {
        List<Order> list = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            int uid = rs.getInt("uid");
            Order order = new Order(id, new UserDAO().get(uid));
            list.add(order);
        }
        return list;
    }

    @Override
    public <T> T add(T t) {
        String sql = "insert into order_ values(null, ?)";
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            Order order = (Order) t;
            ps.setInt(1, order.getUser().getId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                order.setId(rs.getInt(1));
            }
            System.out.println(order.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> void update(T t) {
        ;
    }

}

