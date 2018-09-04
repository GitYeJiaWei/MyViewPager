package com.example.yjw.intent;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/18 0018.
 * 序列化 Serializable
 */

public class Person implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
