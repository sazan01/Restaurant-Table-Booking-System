package com.onlinerestauranttablebooking.Model;

public class ContactModel {
    private String name,email,phone,sub,message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactModel(String name, String email, String phone, String sub, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sub = sub;
        this.message = message;
    }


}
