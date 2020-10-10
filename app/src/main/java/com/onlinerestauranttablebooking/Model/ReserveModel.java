package com.onlinerestauranttablebooking.Model;

public class ReserveModel {
    private String name, date, time, num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public ReserveModel(String name, String date, String time, String num) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.num = num;
    }
}
