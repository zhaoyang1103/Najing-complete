package com.example.nanjing.ws_java.etc.bean;
/*
 * Created by 王森 on 2019/3/30.
 */

public class YueBean {
    private Integer id;
    private String chebiao;
    private String chepai;
    private String chezhu;
    private Integer balance;

    public YueBean(Integer id, String chebiao, String chepai, String chezhu, Integer balance) {
        this.id = id;
        this.chebiao = chebiao;
        this.chepai = chepai;
        this.chezhu = chezhu;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChebiao() {
        return chebiao;
    }

    public void setChebiao(String chebiao) {
        this.chebiao = chebiao;
    }

    public String getChepai() {
        return chepai;
    }

    public void setChepai(String chepai) {
        this.chepai = chepai;
    }

    public String getChezhu() {
        return chezhu;
    }

    public void setChezhu(String chezhu) {
        this.chezhu = chezhu;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
