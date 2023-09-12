package com.example.myapp_2.Data.Discount_Get_table_Pofile;

public class StocksModel {
    String title, description;
    int image;

    public StocksModel(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
