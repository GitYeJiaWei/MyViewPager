package com.example.yjw.OnRefreshList;

/**
 * @author YJW
 * @create 2018/7/9
 * @Describe
 */
public class OnRefreshModel {
    private String time;
    private String content;

    public OnRefreshModel(String ti, String cont){
        this.time = ti;
        this.content = cont;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
