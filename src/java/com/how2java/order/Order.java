package com.how2java.order;

import com.how2java.login.User;

public class Order {
    private int id;
    private User user;

    public Order() {
    }

    public Order(User user) {
        this.user = user;
    }

    public Order(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
