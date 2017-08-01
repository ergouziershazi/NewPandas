package com.newpandas.model.entity;

/**
 * Created by dell on 2017/7/20.
 */

public class NickNameBean {


    /**
     * code : 0
     * content : {"nickname":"央视网友8l6p4e8q"}
     */

    private int code;
    private ContentBean content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * nickname : 央视网友8l6p4e8q
         */

        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
