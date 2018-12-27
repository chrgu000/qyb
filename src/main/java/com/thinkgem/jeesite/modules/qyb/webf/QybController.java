package com.thinkgem.jeesite.modules.qyb.webf;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.fastweixin.api.SessionApi;
import com.thinkgem.jeesite.common.fastweixin.api.config.ApiConfig;
import com.thinkgem.jeesite.common.fastweixin.api.response.SnsTokenResponse;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.BaseResponse;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.XmlMapHandle;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.wxpay.WXPay;
import com.thinkgem.jeesite.common.wxpay.WXPayConfigImpl;
import com.thinkgem.jeesite.common.wxpay.WXPayConstants;
import com.thinkgem.jeesite.common.wxpay.WXPayUtil;
import com.thinkgem.jeesite.modules.qyb.entity.*;
import com.thinkgem.jeesite.modules.qyb.service.CompanyService;
import com.thinkgem.jeesite.modules.qyb.service.CooperationService;
import com.thinkgem.jeesite.modules.qyb.service.WCommentService;
import com.thinkgem.jeesite.modules.qyb.service.WUserService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${frontPath}/qyb")
public class QybController extends BaseController {

  @Resource
  private CompanyService companyService;
  @Resource
  private CooperationService cooperationService;

  @Resource
  private WUserService userService;

  @Resource
  private WCommentService wCommentService;

  @RequestMapping(value = {"company/info"})
  public String companyInfo(HttpServletRequest request, HttpServletResponse response, String id) {
    Company company = companyService.get(id);
    return renderString(response, BaseResponse.success(company));
  }

  @RequestMapping(value = {"company/list"})
  public String companyList(HttpServletRequest request, HttpServletResponse response, Company company) {
    Page<Company> page = companyService.findPage(new Page<>(request, response), company);
    return renderString(response, BaseResponse.success("success", page));
  }

  @RequestMapping(value = {"cooperation/detail"})
  public String coopDetail(Cooperation entity, HttpServletRequest request, HttpServletResponse response) {
    Cooperation cooperation = cooperationService.getDetail(entity.getId());
    return renderString(response, BaseResponse.success(cooperation));
  }

  @RequestMapping(value = "company/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  public String cpySave(HttpServletResponse response, @RequestBody Company entity) {
    companyService.save(entity);
    return renderString(response, BaseResponse.success("保存成功"));
  }

  @RequestMapping(value = {"cooperation/findCotList"})
  public String findCotList(String cpyId, HttpServletResponse response) {
    List<Cooperation> list = cooperationService.findCotList(cpyId);
    return renderString(response, BaseResponse.success(list));
  }

  @RequestMapping(value = {"user/getAdvCount"})
  public String getAdvCount(String id, HttpServletResponse response) {
    Integer advCount = userService.getAdvCount(id);
    return renderString(response, BaseResponse.success(advCount));
  }

  @RequestMapping(value = "getAdver", produces = "application/json")
  public String getAdver(HttpServletResponse response) {
    Adver adver = cooperationService.getAdver();

    List<Adver> adverList = new ArrayList<>();

    String photo = adver.getPhoto();
    if (StringUtils.isNoneBlank(photo)) {
      String[] array = adver.getPhoto().split("\\|");
      for (String str : array)
        if (StringUtils.isNoneBlank(str)) {
          adverList.add(new Adver(str));
        }
    }
    return renderString(response, BaseResponse.success("success", adverList));
  }

  @RequestMapping(value = {"user/getByCpyUser"})
  public String getByCpyUser(String cpyId, HttpServletResponse response) {
    List<WUser> list = userService.findByCpy(cpyId);
    return renderString(response, BaseResponse.success(list));
  }

  @RequestMapping(value = {"comment/list"})
  public String getComment(String copId, HttpServletResponse response) {
    List<WComment> list = wCommentService.getByCopId(copId);
    return renderString(response, BaseResponse.success(list));
  }

  @RequestMapping(value = {"wechat/jscode2session"})
  public String getOpenid(HttpServletResponse response, String jsCode) {
    SessionApi sessionApi = new SessionApi(new ApiConfig("", ""));
    //code = "071fUVQd1kfk7z0fR7Nd19MdRd1fUVQt";
    SnsTokenResponse re = sessionApi.executeSGet("sns/jscode2session?appid=wxa2df920f1bbc2e1d&secret=9217ff71cb332917ccf0ba2ffc7f675c&js_code=#&grant_type=authorization_code", jsCode);
    return renderString(response, BaseResponse.success(re));
  }

  @RequestMapping(value = "user/info")
  public String getUserInfo(HttpServletResponse response, WUser entity) {
    WUser user = userService.getByInfo(entity);
    return renderString(response, BaseResponse.success(user));
  }

  @RequestMapping(value = {"cooperation/list"})
  public String list(Cooperation entity, HttpServletRequest request, HttpServletResponse response) {
    Page<Cooperation> page = cooperationService.findPage(new Page<>(request, response), entity);
    return renderString(response, BaseResponse.success(page));
  }

  @RequestMapping(value = "/wx/payment")
  public String payment(HttpServletResponse response, String openid, String total_fee) throws Exception {
    WXPayConfigImpl config = WXPayConfigImpl.getInstance();
    WXPay wxPay = new WXPay(config, false, false);

    Map<String, String> reqData = new HashMap<>();
    reqData.put("appid", config.getAppID());
    reqData.put("mch_id", config.getMchID());
    reqData.put("nonce_str", WXPayUtil.generateNonceStr());
    //reqData.put("nonce_str", "fsafljaewfmlsdfs");
    reqData.put("sign_type", WXPayConstants.MD5);


    reqData.put("body", "Test");
    reqData.put("out_trade_no", WXPayUtil.getPayNo());
    reqData.put("total_fee", total_fee);
    reqData.put("spbill_create_ip", "218.18.52.167");
    reqData.put("notify_url", "http://www.st-serve.cn/qyb/f/qyb/wx/notify");
    reqData.put("trade_type", "JSAPI");
    reqData.put("openid", openid);
    reqData.put("fee_type", "CNY");
    reqData.put("sign", WXPayUtil.generateSignature(reqData, config.getKey(), WXPayConstants.SignType.MD5));




    Map<String, String> resultMap = wxPay.unifiedOrder(reqData);

    Map<String, String> payMap = new HashMap<>();
    payMap.put("appId", config.getAppID());
    payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
    payMap.put("nonceStr", WXPayUtil.generateNonceStr());
    payMap.put("package", "prepay_id=" + resultMap.get("prepay_id"));
    payMap.put("signType", "MD5");
    payMap.put("paySign", WXPayUtil.generateSignature(payMap, config.getKey(), WXPayConstants.SignType.MD5));

    return renderString(response, BaseResponse.success(payMap));
  }

  @RequestMapping(value = "register")
  public String register(HttpServletResponse response, WUser user) {
    userService.save(user);
    return renderString(response, BaseResponse.success("注册成功"));
  }

  @RequestMapping(value = "cooperation/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  public String save(HttpServletResponse response, @RequestBody Cooperation entity) {
    cooperationService.save(entity);
    return renderString(response, BaseResponse.success("发布成功"));
  }

  @RequestMapping(value = "updateViews")
  public String updateViews(String id, HttpServletResponse response) {
    cooperationService.updateViews(id);
    return renderString(response, BaseResponse.success("success"));

  }

  @RequestMapping(value = "/wx/notify")
  public String wxNotify(String id, HttpServletResponse response, HttpServletRequest request) {
    try {
      InputStream inStream = request.getInputStream();
      int _buffer_size = 1024;
      if (inStream != null) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] tempBytes = new byte[_buffer_size];
        int count = -1;
        while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
          outStream.write(tempBytes, 0, count);
        }
        tempBytes = null;
        outStream.flush();
        //将流转换成字符串
        String result = new String(outStream.toByteArray(), "UTF-8");
        //将字符串解析成XML
        Document doc = DocumentHelper.parseText(result);
        //将XML格式转化成MAP格式数据
        Map<String, Object> resultMap = XmlMapHandle.Dom2Map(doc);
        //后续具体自己实

        String resultCode = (String) resultMap.get("result_code");
        String openid=(String) resultMap.get("openid");

        WUser user=new WUser();
        user.setOpenid(openid);

        if (resultCode.equals("SUCCESS")) {
          Integer totalFee = (Integer) resultMap.get("total_fee") / 100;
          if (totalFee == 99) {
            //推广经理
            user.setVipLevel(2);

          } else if (totalFee == 500) {
            //VIP
            user.setVipLevel(3);
            user.setAdvCount(50);
            user.setAdvCount(380);
          } else if (totalFee == 1000) {
            //SVIP
            user.setVipLevel(4);
            user.setAdvCount(200);
            user.setAdvCount(1000);
          } else if (totalFee == 1800) {
            //SSVIP
            user.setVipLevel(5);
            user.setAdvCount(0);
            user.setAdvCount(0);
          }
          userService.updateByOpenid(user);
        }
        logger.error(JSON.toJSONString(resultMap));
      }
      //通知微信支付系统接收到信息
      return "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg> </xml>";
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    //如果失败返回错误，微信会再次发送支付信息
    return "fail";
  }


  @RequestMapping(value = "user/save")
  public String userSave(WUser user,HttpServletResponse response){
     userService.save(user );
     return renderString(response,BaseResponse.success("保存成功"));
  }

}
