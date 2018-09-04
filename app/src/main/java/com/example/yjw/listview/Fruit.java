package com.example.yjw.listview;

/**
 * Created by YJW on 2018/1/12.
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name,int imageId){
        this.name=name;
        this.imageId =imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return  imageId;
    }

}
