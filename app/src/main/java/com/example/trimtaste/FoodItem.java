package com.example.trimtaste;

public class FoodItem {
    private String text;
    private String name;

    private double price;

    public FoodItem(String text, double price) {
        this.text = text;
        this.price = price;

    }
    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public double getPrice() {
        return price;
    }
}

