package com.example.myapp_2.Data.Discount_Get_table_Pofile;

public class RestaurantModel {
    String name, adress, workTime, kitchenType;
    int image;

    public RestaurantModel(String name, String adress, String workTime, String kitchenType, int image) {
        this.name = name;
        this.adress = adress;
        this.workTime = workTime;
        this.kitchenType = kitchenType;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getWorkTime() {
        return workTime;
    }

    public String getKitchenType() {
        return kitchenType;
    }

    public int getImage() {
        return image;
    }
}
