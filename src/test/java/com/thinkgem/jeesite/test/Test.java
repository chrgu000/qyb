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

   // final WXPayConfigImpl config = new WXPayConfigImpl(appid, mch_id,key);

   // WXPay wxPay = new WXPay(config);

System.out.println(WXPayUtil.getCurrentTimestamp());

    //System.out.println(JSON.toJSONString(resultMap));

  }
}
