package com.how2java.orderItem;

import com.how2java.DaoPackage.Dao;
import com.how2java.order.OrderDao;
import com.how2java.product.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao extends Dao {
    public OrderItemDao() {
        super();
        this.table = "orderitem";
    }

    @Override
    public List<OrderItem> result(ResultSet rs) throws SQLException {
        List<OrderItem> list = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            int pid = rs.getInt("pid");
            int num = rs.getInt("num");
            int oid = rs.getInt("oid");
            OrderItem oi = new OrderItem(id, ProductDAO.getInstance().get(pid), num, OrderDao.getInstance().get(oid));
            list.add(oi);
        }
        return list;
    }

    @Override
    public <T> T add(T t) {
        String sql = "insert into " + this.table + " values(null, ?, ?, ?)";
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            OrderItem orderItem = (OrderItem) t;
            ps.setInt(1, orderItem.getProduct().getId());
            ps.setInt(2, orderItem.getNum());
            ps.setInt(3, orderItem.getOrder().getId());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                int id = rs.getInt(1);
                orderItem.setId(id);
            }

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
