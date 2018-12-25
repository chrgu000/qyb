package com.thinkgem.jeesite.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.wxpay.WXPay;
import com.thinkgem.jeesite.common.wxpay.WXPayConfigImpl;
import com.thinkgem.jeesite.common.wxpay.WXPayConstants;
import com.thinkgem.jeesite.common.wxpay.WXPayConstants.SignType;
import com.thinkgem.jeesite.common.wxpay.WXPayUtil;

import java.util.HashMap;
import java.util.Map;

public class Test {

  public static void main(String[] args) throws Exception {
    //测试文件

    String appid = "wxa2df920f1bbc2e1d";
    String mch_id = "1521746531";
    String key="XcxmT58Kdp5jOEUCLLNK6pj07RcPqXXK";

    final WXPayConfigImpl config = new WXPayConfigImpl(appid, mch_id,key);

    WXPay wxPay = new WXPay(config);

    Map<String, String> reqData = new HashMap<>();
    reqData.put("appid", config.getAppID());
    reqData.put("mch_id", config.getMchID());
    reqData.put("nonce_str", WXPayUtil.generateNonceStr());
    reqData.put("sign_type", WXPayConstants.MD5);

    reqData.put("body", "企营宝-会员充值中心");
    reqData.put("out_trade_no", "1");
    reqData.put("total_fee", "1");
    reqData.put("spbill_create_ip", "218.18.52.167");
    reqData.put("notify_url", "http://");
    reqData.put("trade_type", "JSAPI");
    reqData.put("openid", "openid");

    reqData.put("sign", WXPayUtil.generateSignature(reqData, config.getKey(), SignType.MD5));

    Map<String, String> map = wxPay.unifiedOrder(reqData);

    System.out.println(JSON.toJSONString(map));

  }
}
