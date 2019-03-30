package com.example.nanjing.st_java.bean;

import java.util.List;

public class Get_weather {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * ROWS_DETAIL : [{"temperature":"-5~-4","weather":"中雨","WData":"2019-03-29"},{"temperature":"-5~-1","weather":"多云","WData":"2019-03-30"},{"temperature":"-1~0","weather":"大雨","WData":"2019-03-31"},{"temperature":"-3~0","weather":"小雨","WData":"2019-04-01"},{"temperature":"-5~0","weather":"小雨","WData":"2019-04-02"},{"temperature":"-1~0","weather":"多云","WData":"2019-04-03"},{"temperature":"-4~0","weather":"小雨","WData":"2019-04-04"}]
     * WCurrent : -5
     */

    private String ERRMSG;
    private String RESULT;
    private int WCurrent;
    private List<ROWSDETAILBean> ROWS_DETAIL;

    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }

    public String getRESULT() {
        return RESULT;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public int getWCurrent() {
        return WCurrent;
    }

    public void setWCurrent(int WCurrent) {
        this.WCurrent = WCurrent;
    }

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * temperature : -5~-4
         * weather : 中雨
         * WData : 2019-03-29
         */

        private String temperature;
        private String weather;
        private String WData;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWData() {
            return WData;
        }

        public void setWData(String WData) {
            this.WData = WData;
        }
    }
}
