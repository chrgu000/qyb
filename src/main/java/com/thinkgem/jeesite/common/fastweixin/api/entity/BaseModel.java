package com.thinkgem.jeesite.common.fastweixin.api.entity;

import com.thinkgem.jeesite.common.fastweixin.util.JSONUtil;

/**
 * 抽象实体类
 *
 * @author peiyu
 */
public abstract class BaseModel implements Model {
    @Override
    public String toJsonString() {
        return JSONUtil.toJson(this);
    }
}