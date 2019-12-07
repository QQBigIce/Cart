package com.how2java.login;

import com.how2java.DaoPackage.Dao;
import com.how2java.product.Product;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Dao {
    public UserDAO() {
        super();
        this.table = "user";
    }

    @Override
    public List<User> result(ResultSet rs) throws SQLException {
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            User u = new User(id, name, password);
            list.add(u);
        }
        return list;
    }

    @Override
    public <T> T add(T t) {
        User u = (User) t;
        String sql = "insert into " + table + " values(?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setInt(1, u.getId());
            ps.setString(2, u.getName());
            ps.setString(3, u.getPassword());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return ((T) new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> void update(T t) {
        User u = (User) t;
        String sql = "update " + this.table + " set name = ?, price = ? where id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, u.getName());
            ps.setString(2, u.getName());
            ps.setInt(3, u.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
