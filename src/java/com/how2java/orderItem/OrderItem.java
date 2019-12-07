package com.how2java.orderItem;

import com.how2java.order.Order;
import com.how2java.product.Product;

import java.io.Serializable;
import java.util.Objects;

public class OrderItem implements Serializable {
    private int id;
    private Product product;
    private int num;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(int id, Product product, int num) {
        this.id = id;
        this.product = product;
        this.num = num;
    }

    public OrderItem(int id, Product product, int num, Order order) {
        this.id = id;
        this.product = product;
        this.num = num;
        this.order = order;
    }

    public OrderItem(Product product, int num) {
        this.product = product;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", num=" + num +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
                num == orderItem.num &&
                product.equals(orderItem.product);
    }
}
