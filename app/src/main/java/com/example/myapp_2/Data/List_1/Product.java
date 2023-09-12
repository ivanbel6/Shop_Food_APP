package com.example.myapp_2.Data.List_1;

public class Product {
    private double new_price;
    private String name;
    private String description;
    private int imageResource;
    private float rating;
    private double price;

    public Product(String name, String description, int imageResource, float rating, double price) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.rating = rating;
        this.price = price;
    }

    public Product(String name, String description, int imageResource, double price) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.rating = 0.0f;
        this.price = price;
    }
    public Product(String name, String description, int imageResource, double price ,double new_price  ) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.rating = 0.0f;
        this.price = price;
        this.new_price = new_price;
    }

    public double getNew_price() {
        return new_price;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}