package com.example.yjw.contentResolver;

/**
 * Created by YJW on 2018/2/1.
 */

public class User {
    private String name;
    private String phone;

    public User(String name,String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
