package com.how2java.DaoPackage;

import com.how2java.product.Product;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao {
    /**
     * 构造方法：加载mysql驱动
     */
    protected String table;
    public Dao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?characterEncoding=utf-8&serverTimezone=GMT", "root", "admin");
    }

    // 通过查询语句查询到的商品
    public abstract <T> List<T> result(ResultSet rs) throws SQLException;
    // 向数据库中添加数据
    public abstract <T> T add(T t);
    //    删除数据库中的数据
    public boolean delete(Serializable serializable) {
        String sql = new String();
        if (serializable instanceof String) {
            sql = "delete from " + this.table +
                    " where name = " + (String) serializable;
        } else if (serializable instanceof Integer) {
            sql = "delete from " + this.table +
                    " where id =" + (int) serializable;
        } else {
            return false;
        }
        try (Connection c = getConnection();
             Statement s = c.createStatement();) {

            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    // 修改数据库中的数据
    public abstract <T> void update(T t);

    //根据id查询
    public <T> T get(int id){
        String sql = "select * from " + this.table + " where id = " + id;
        try(Connection c = getConnection();
            Statement s = c.createStatement();) {

            ResultSet rs = s.executeQuery(sql);
            if (rs.next()){
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                return (T) new Product(id, name, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据name查询
    public <T> List<T> get(String name) {
        List<T> list = null;
        String sql = "select * from " + this.table +
                " where name = '" + name + "'";
        try (Connection c = getConnection();
             Statement s = c.createStatement();) {

            ResultSet rs = s.executeQuery(sql);
            list = result(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //显示数据库中有多少条数据
    public int getTotal() {
        int total = 0;
        String sql = "select count(*) from " + table;
        try (Connection c = getConnection();
             Statement s = c.createStatement();) {

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }
    //显示数据库中从start开始的count条数据
    public <T> List<T> list(int start, int count) {
        List<T> list = new ArrayList<>();
        String sql = "select * from " + table + " order by id desc limit ?, ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();
            list = result(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //显示数据库中所有内容
    public <T> List<T> list(){
        return list(0, getTotal());
    }
}
