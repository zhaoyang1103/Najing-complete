package com.example.nanjing.ws_java.etc.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 * Created by 王森 on 2019/3/30.
 */
@DatabaseTable(tableName = "jilu")
public class JiluBean {
    @DatabaseField(columnName = "id",generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "user")
    private String user;
    @DatabaseField(columnName = "chepai")
    private String chepai;
    @DatabaseField(columnName = "balance")
    private Integer balance;
    @DatabaseField(columnName = "time")
    private String time;

    public JiluBean() {
    }

    public JiluBean(Integer id, String user, String chepai, Integer balance, String time) {
        this.id = id;
        this.user = user;
        this.chepai = chepai;
        this.balance = balance;
        this.time = time;
    }

    public JiluBean(String user, String chepai, Integer balance, String time) {
        this.user = user;
        this.chepai = chepai;
        this.balance = balance;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getChepai() {
        return chepai;
    }

    public void setChepai(String chepai) {
        this.chepai = chepai;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "JiluBean{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", chepai='" + chepai + '\'' +
                ", balance=" + balance +
                ", time='" + time + '\'' +
                '}';
    }
}
