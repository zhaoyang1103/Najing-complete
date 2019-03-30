package com.example.nanjing.ws_java.etc.bean;
/*
 * Created by 王森 on 2019/3/30.
 */

import java.util.List;

public class GaoBean {

    /**
     * ERRMSG : 成功
     * RESULT : S
     * ROWS_DETAIL : [{"content":"公告内容","id":1,"publish_time":"2019-01-18 08:10:14.0","title":"旅游公告"},{"content":"注意事项","id":2,"publish_time":"2019-01-18 08:10:37.0","title":"注意事项"}]
     */

    private List<ROWSDETAILBean> ROWS_DETAIL;

    public List<ROWSDETAILBean> getROWS_DETAIL() {
        return ROWS_DETAIL;
    }

    public void setROWS_DETAIL(List<ROWSDETAILBean> ROWS_DETAIL) {
        this.ROWS_DETAIL = ROWS_DETAIL;
    }

    public static class ROWSDETAILBean {
        /**
         * content : 公告内容
         * id : 1
         * publish_time : 2019-01-18 08:10:14.0
         * title : 旅游公告
         */

        private String content;
        private int id;
        private String publish_time;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
