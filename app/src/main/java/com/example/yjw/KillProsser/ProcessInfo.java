package com.example.yjw.KillProsser;

import android.graphics.drawable.Drawable;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */
public class ProcessInfo {
    private String packageName;
    private long size;
    private String name;
    private Drawable icon;
    private boolean uesrProcess;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isUesrProcess() {
        return uesrProcess;
    }

    public void setUesrProcess(boolean uesrProcess) {
        this.uesrProcess = uesrProcess;
    }
}
