package com.example.nanjing.zy_java.bean;

/**
 * Created by 昭阳 on 2019/3/30.
 */
public class Gv_bean {
    private String name;
    private int index;
    private  int threshold;

    public Gv_bean(String name, int index, int threshold) {
        this.name = name;
        this.index = index;
        this.threshold = threshold;
    }

    public Gv_bean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
