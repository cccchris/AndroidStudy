package com.example.yh.gridview;

/**
 * Created by yh on 2018/1/31.
 */

public class Icon {
    private String name;
    private int  icon;

    public Icon(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
