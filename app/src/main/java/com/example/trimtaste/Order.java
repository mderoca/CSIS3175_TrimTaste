package com.example.trimtaste;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String customerName;
    private String foodItem;
    private String size;
    private int quantity;

    public Order(int id, String customerName, String foodItem, String size, int quantity) {
        this.id = id;
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.size = size;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}