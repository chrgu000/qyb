package com.thinkgem.jeesite.common.fastweixin.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author peiyu
 */
public class UserReadHour extends BaseDataCube {

    @JSONField(name = "ref_hour")
    private Integer refHour;
    @JSONField(name = "int_page_read_user")
    private Integer intPageReadUser;
    @JSONField(name = "int_page_read_count")
    private Integer intPageReadCount;
    @JSONField(name = "ori_page_read_user")
    private Integer oriPageReadUser;
    @JSONField(name = "ori_page_read_count")
    private Integer oriPageReadCount;
    @JSONField(name = "share_user")
    private Integer shareUser;
    @JSONField(name = "share_count")
    private Integer shareCount;
    @JSONField(name = "add_to_fav_user")
    private Integer addToFavUser;
    @JSONField(name = "add_to_fav_count")
    private Integer addToFavCount;

    public Integer getRefHour() {
        return refHour;
    }

    public void setRefHour(Integer refHour) {
        this.refHour = refHour;
    }

    public Integer getIntPageReadUser() {
        return intPageReadUser;
    }

    public void setIntPageReadUser(Integer intPageReadUser) {
        this.intPageReadUser = intPageReadUser;
    }

    public Integer getIntPageReadCount() {
        return intPageReadCount;
    }

    public void setIntPageReadCount(Integer intPageReadCount) {
        this.intPageReadCount = intPageReadCount;
    }

    public Integer getOriPageReadUser() {
        return oriPageReadUser;
    }

    public void setOriPageReadUser(Integer oriPageReadUser) {
        this.oriPageReadUser = oriPageReadUser;
    }

    public Integer getOriPageReadCount() {
        return oriPageReadCount;
    }

    public void setOriPageReadCount(Integer oriPageReadCount) {
        this.oriPageReadCount = oriPageReadCount;
    }

    public Integer getShareUser() {
        return shareUser;
    }

    public void setShareUser(Integer shareUser) {
        this.shareUser = shareUser;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getAddToFavUser() {
        return addToFavUser;
    }

    public void setAddToFavUser(Integer addToFavUser) {
        this.addToFavUser = addToFavUser;
    }

    public Integer getAddToFavCount() {
        return addToFavCount;
    }

    public void setAddToFavCount(Integer addToFavCount) {
        this.addToFavCount = addToFavCount;
    }
}
