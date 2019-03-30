package com.example.nanjing.st_java.bean;

public class TQXXBean {
    private int icon;
    private String name;
    private String value1;
    private String value2;

    public TQXXBean(int icon, String name, String value1, String value2) {
        this.icon = icon;
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
