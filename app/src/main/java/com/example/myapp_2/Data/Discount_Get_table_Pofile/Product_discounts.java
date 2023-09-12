package com.example.myapp_2.Data.Discount_Get_table_Pofile;

public class Product_discounts {
    private int id;
    private String name;
    private String image;
    private float oldPrice;
    private float newPrice;
    private String discount;

    public Product_discounts(int id, String name, String image, float oldPrice, float newPrice, String discount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.discount = discount;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
