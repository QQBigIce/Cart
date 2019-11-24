package com.how2java.product;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static ProductDAO productDAO;
    public static ProductDAO getInstance(){
        if (productDAO == null){
            productDAO = new ProductDAO();
        }
        return productDAO;
    }
    private ProductDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?characterEncoding=utf-8&serverTimezone=GMT", "root", "admin");
    }

    //通过查询语句查询到的商品
    public List<Product> result(ResultSet rs) throws SQLException {
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String pName = rs.getString("name");
            float price = rs.getFloat("price");
            Product p = new Product(id, pName, price);
            list.add(p);
        }
        return list;
    }

    //向数据库中添加数据
    public Product add(Product product) {
        String sql = "insert into product values(?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setFloat(3, product.getPrice());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return (new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    删除数据库中的数据
    public boolean delete(Serializable serializable) {
        String sql = new String();
        if (serializable instanceof String) {
            sql = "delete from productproduct where name = " + (String) serializable;
        } else if (serializable instanceof Integer) {
            sql = "delete from product where id =" + (int) serializable;
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

    //修改数据库中的数据
    public void update(Product product) {
        String sql = "update product set name = ?, price = ? where id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setInt(3, product.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //根据name查询
    public List<Product> get(String name) {
        List<Product> list = new ArrayList<>();
        Product p = null;
        String sql = "select * from product where name = " + name;
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
        String sql = "select count(*) from product";
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
    public List<Product> list(int start, int count) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product order by id desc limit ?, ?";
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
    public List<Product> list(){
        return list(0, getTotal());
    }

}
