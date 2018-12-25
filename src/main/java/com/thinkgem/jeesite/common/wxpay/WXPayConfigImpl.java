package com.thinkgem.jeesite.common.wxpay;

import java.io.InputStream;

public class WXPayConfigImpl extends WXPayConfig {

  private String appid;
  private String mch_id;
  private String key;


  public WXPayConfigImpl() {

  }

  public WXPayConfigImpl(String appid, String mch_id,String key) {
    this.appid = appid;
    this.mch_id = mch_id;
    this.key=key;
  }

  @Override
  public String getAppID() {
    return appid;
  }

  @Override
  public String getMchID() {
    return mch_id;
  }

  @Override
  public  String getKey() {
    return key;
  }

  @Override
  public InputStream getCertStream() {
    return null;
  }

  @Override
  public  IWXPayDomain getWXPayDomain() {
    return null;
  }
}
