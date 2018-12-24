package com.thinkgem.jeesite.common.fastweixin.api;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.fastweixin.api.config.ApiConfig;
import com.thinkgem.jeesite.common.fastweixin.api.enums.ResultType;
import com.thinkgem.jeesite.common.fastweixin.api.response.BaseResponse;
import com.thinkgem.jeesite.common.fastweixin.api.response.GetSendMessageResponse;
import com.thinkgem.jeesite.common.fastweixin.api.response.SnsTokenResponse;
import com.thinkgem.jeesite.common.fastweixin.util.BeanUtil;
import com.thinkgem.jeesite.common.fastweixin.util.JSONUtil;
import com.thinkgem.jeesite.common.fastweixin.util.NetWorkCenter;

public class SessionApi extends BaseAPI {

  public SessionApi(ApiConfig config) {
    super(config);
  }

  /**
   * 通用get请求
   *
   * @param url 地址，其中token用#代替
   * @return 请求结果
   */
  public SnsTokenResponse executeSGet(String url, String code) {
    BaseResponse response;
    BeanUtil.requireNonNull(url, "url is null");
    //需要传token
    String getUrl = url.replace("#", code);
    response = NetWorkCenter.get(getUrl);
    String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
    return JSONUtil.toBean(resultJson, SnsTokenResponse.class);
  }

}