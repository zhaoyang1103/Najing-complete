package com.example.nanjing.st_java.bean;

public class GRZXBean {
    private int icon;
    private String carnumber;
    private String value;

    public GRZXBean(int icon, String carnumber, String value) {
        this.icon = icon;
        this.carnumber = carnumber;
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}