package com.how2java.orderItem;

import com.how2java.product.Product;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int id;
    private Product product;
    private int num;

    public OrderItem() {
    }

    public OrderItem(int id, Product product, int num) {
        this.id = id;
        this.product = product;
        this.num = num;
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
}
