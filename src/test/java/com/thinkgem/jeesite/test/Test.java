package com.thinkgem.jeesite.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.fastweixin.api.config.ApiConfig;
import com.thinkgem.jeesite.common.wxpay.WXPay;
import com.thinkgem.jeesite.common.wxpay.WXPayConfigImpl;
import com.thinkgem.jeesite.common.wxpay.WXPayConstants;
import com.thinkgem.jeesite.common.wxpay.WXPayConstants.SignType;
import com.thinkgem.jeesite.common.wxpay.WXPayUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Test {

  public static void main(String[] args) throws Exception {
    //测试文件


    String appid = "wxa2df920f1bbc2e1d";
    String mch_id = "1521746531";
    String key="XcxmT58Kdp5jOEUCLLNK6pj07RcPqXXK";

   /* final WXPayConfigImpl config = new WXPayConfigImpl(appid, mch_id,key);

   /* WXPay wxPay = new WXPay(config);
*/
    ApiConfig apiConfig=new ApiConfig(WXPayConstants.APP_ID,WXPayConstants.SECRET);

    System.out.println(apiConfig.getAccessToken());

System.out.println(WXPayUtil.getCurrentTimestamp());

    //System.out.println(JSON.toJSONString(resultMap));


    BigDecimal bigDecimal=new BigDecimal("99");
    BigDecimal bigDecimal1=   new BigDecimal("0.35");
    System.out.println(bigDecimal.multiply(bigDecimal1));

    //System.out.println(bigDecimal.divide(bigDecimal2,2,RoundingMode.HALF_UP));

  }
}
