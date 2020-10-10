package com.onlinerestauranttablebooking.Model;

public class ViewDishModel {
    private String dishName, Price, Category, dishImage;

    public ViewDishModel(String dishname, String price, String category, String dishImage) {
        dishName = dishname;
        Price = price;
        Category = category;
        dishImage = dishImage;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }
}