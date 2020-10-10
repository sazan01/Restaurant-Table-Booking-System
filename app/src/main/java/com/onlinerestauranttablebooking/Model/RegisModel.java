package com.onlinerestauranttablebooking.Model;

public class RegisModel {
    private String _id, name, email, pass, re_pass, usertype;

    public RegisModel(String _id, String name, String email, String pass, String re_pass, String usertype) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.re_pass = re_pass;
        this.usertype = usertype;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRe_pass() {
        return re_pass;
    }

    public void setRe_pass(String re_pass) {
        this.re_pass = re_pass;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}