package com.example.trimtaste;

public class User_MenuItem {
    private int id;
    private static String name;
    private static double price;

    public User_MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static double getPrice() {
        return price;
    }
}
