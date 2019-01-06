package com.thinkgem.jeesite.modules.qyb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Msg extends DataEntity<Msg> {
    private String content;
    private String userId;

    public Msg(){

    }

    public Msg(String userId) {
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
