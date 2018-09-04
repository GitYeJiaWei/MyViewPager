package com.example.yjw.intent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/3/18 0018.
 * 序列化parcelable
 */

public class Students implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name); //写出name
        dest.writeInt(age);     //写出age
    }

    public static final Parcelable.Creator<Students> CREATOR =new Parcelable.Creator<Students>(){

        @Override
        public Students createFromParcel(Parcel source) {
            Students students =new Students();
            students.name =source.readString();     //读取name
            students.age =source.readInt();         //读取age
            return students;
        }

        @Override
        public Students[] newArray(int size) {
            return new Students[size];
        }
    };
}
