package com.example.nanjing.fragment.home;
/*
 * Created by 王森 on WangSen.
 */

public class HomeBean {
    private int home_img;
    private String home_text;

    public HomeBean(int home_img, String home_text) {
        this.home_img = home_img;
        this.home_text = home_text;
    }

    public int getHome_img() {
        return home_img;
    }

    public void setHome_img(int home_img) {
        this.home_img = home_img;
    }

    public String getHome_text() {
        return home_text;
    }

    public void setHome_text(String home_text) {
        this.home_text = home_text;
    }
}
