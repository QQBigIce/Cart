package com.how2java.product;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private int id;
    private String name;
    private float price;

    public Product() {
    }

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ("id: " + this.id + "\tname: " + this.name + "\tprice: " + this.price + "\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return  false;
        Product product = (Product) o;
        return id == product.id &&
                Float.compare(product.price, price) == 0 &&
                this.name.equals(product.getName());
    }
}
