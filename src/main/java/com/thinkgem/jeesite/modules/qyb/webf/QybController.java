package com.thinkgem.jeesite.modules.qyb.webf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.fastweixin.api.SessionApi;
import com.thinkgem.jeesite.common.fastweixin.api.response.SnsTokenResponse;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.BaseResponse;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.XmlMapHandle;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.wxpay.WXPay;
import com.thinkgem.jeesite.common.wxpay.WXPayConfigImpl;
import com.thinkgem.jeesite.common.wxpay.WXPayConstants;
import com.thinkgem.jeesite.common.wxpay.WXPayUtil;
import com.thinkgem.jeesite.modules.qyb.entity.*;
import com.thinkgem.jeesite.modules.qyb.service.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
    private RecommendService recommendService;
    @Resource
    private WUserService userService;
    @Resource
    private WCommentService wCommentService;
    @Resource
    private MsgService msgService;


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


    @RequestMapping(value = {"user/getPhCount"})
    public String getPhCount(String id, HttpServletResponse response) {
        Integer phCount = userService.getPhCount(id);
        return renderString(response, BaseResponse.success(phCount));
    }
    @RequestMapping(value = {"user/subPh"})
    public String subPh(String id, HttpServletResponse response) {
         userService.subPh(id);
        return renderString(response, BaseResponse.success("success"));
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

    @RequestMapping(value = "wx/getCode")
    public void getCode(WUser user, HttpServletResponse response) {
        try {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + SpringContextHolder.apiConfig.getAccessToken());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", user.getId());
            paramJson.put("page", "pages/authorize");
            paramJson.put("width", 430);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */
            //response.get
            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = response.getOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"comment/list"})
    public String getComment(String copId, HttpServletResponse response) {
        List<WComment> list = wCommentService.getByCopId(copId);
        return renderString(response, BaseResponse.success(list));
    }

    @RequestMapping(value = {"wechat/jscode2session"})
    public String getOpenid(HttpServletResponse response, String jsCode) {
        SessionApi sessionApi = new SessionApi();
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

        String way = entity.getWay();
        if (StringUtils.isNoneBlank(way)) {
            if (way.equals("1")) {
                entity.setWay("业务合作");
            } else if (way.equals("2")) {
                entity.setWay("渠道招商");
            } else if (way.equals("3")) {
                entity.setWay("联合活动");
            } else if (way.equals("4")) {
                entity.setWay("代理加盟");
            } else {
                entity.setWay(null);
            }
        }
        Page<Cooperation> page = cooperationService.findPage(new Page<>(request, response), entity);
        return renderString(response, BaseResponse.success(page));
    }


    @RequestMapping(value = "/wx/transfers")
    public String transfers(HttpServletResponse response, String openid, String total_fee)throws Exception{
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        WXPay wxPay = new WXPay(config, false, false);
        Map<String, String> reqData = new HashMap<>();
        reqData.put("mch_appid", config.getAppID());
        reqData.put("mchid", config.getMchID());
        reqData.put("nonce_str", WXPayUtil.generateNonceStr());
        //reqData.put("sign_type", WXPayConstants.MD5);
        reqData.put("partner_trade_no", WXPayUtil.getPayNo());
        reqData.put("openid", openid);

        reqData.put("check_name", "NO_CHECK");
        reqData.put("amount","1");
        reqData.put("desc","xx");
        reqData.put("spbill_create_ip","47.105.199.35");

        reqData.put("sign", WXPayUtil.generateSignature(reqData, config.getKey(), WXPayConstants.SignType.MD5));

        logger.error(JSON.toJSONString(reqData));
        Map<String, String> resultMap = wxPay.transfers(reqData);
        return renderString(response, BaseResponse.success(resultMap));
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
    public String register(HttpServletResponse response, WUser user) throws Exception {
      //user.setName(URLEncoder.encode(user.getName(), "utf-8"));
        userService.save(user);
        return renderString(response, BaseResponse.success("注册成功"));
    }

    @RequestMapping(value = "cooperation/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String save(HttpServletResponse response, @RequestBody Cooperation entity) {
        cooperationService.save(entity);
        return renderString(response, BaseResponse.success("发布成功"));
    }

    @RequestMapping(value = "saveRecommend")
    public String saveRecommend(String referrerUserId, String applicantUserId, HttpServletResponse response) {

        if (StringUtils.isNoneBlank(referrerUserId) && StringUtils.isNoneBlank(applicantUserId)) {

            recommendService.save(new Recommend(referrerUserId, applicantUserId, "1"));

            //找到直推人
            Recommend recommend = recommendService.getByAp(referrerUserId);
            if (recommend != null) {
                recommendService.save(new Recommend(recommend.getReferrerUserId(), applicantUserId, "2"));
            }
        }

        return renderString(response, BaseResponse.success("success"));
    }

    @RequestMapping(value = "updateViews")
    public String updateViews(String id, HttpServletResponse response) {
        cooperationService.updateViews(id);
        return renderString(response, BaseResponse.success("success"));

    }

    @RequestMapping(value = "uploadFile")
    public String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String userId) throws Exception {
        if (file != null) {// 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            String suffix = "." + fileName.substring(fileName.lastIndexOf(".") + 1);
            fileName = userId + suffix;
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    path = realPath + System.getProperty("file.separator") + "userfiles" + System.getProperty("file.separator") + userId + System.getProperty("file.separator");
                    File filePaht = new File(path);
                    if (!filePaht.exists()) {
                        filePaht.mkdirs();
                    }
                    path = path + fileName;
                    System.out.println("存放图片文件的路径:" + path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
                } else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            } else {
                System.out.println("文件类型为空");
                return null;
            }
        } else {
            System.out.println("没有找到相对应的文件");
            return null;
        }
        return renderString(response, BaseResponse.success("success"));
    }

    @RequestMapping(value = "user/save")
    public String userSave(WUser user, HttpServletResponse response) {
        userService.save(user);
        return renderString(response, BaseResponse.success("保存成功"));
    }

    @RequestMapping(value = "wx/notify")
    public void wxNotify(HttpServletResponse response, HttpServletRequest request) throws Exception {


        PrintWriter writer = response.getWriter();
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
            logger.error(JSON.toJSONString(resultMap));
            String resultCode = (String) resultMap.get("result_code");
            String openid = (String) resultMap.get("openid");

            String userId = userService.getUserId(openid);

            WUser user = new WUser(openid);

            Msg msg = new Msg();
            msg.setUserId(userId);


            if (resultCode.equals("SUCCESS")) {
                //Integer totalFee = (Integer) resultMap.get("total_fee") / 100;
                String totalFeeStr = (String) resultMap.get("total_fee");
                Integer totalFee = Integer.parseInt(totalFeeStr) / 100;
/*        if (totalFee == 1) {
          totalFee = 99;
        }
        if (totalFee == 2) {
          totalFee = 500;
        }
        if (totalFee == 3) {
          totalFee = 1000;
        }
        if (totalFee == 4) {
          totalFee = 1800;
        }*/
                if (totalFee == 99) {
                    //推广经理
                    user.setVipLevel(2);
                    msg.setContent("恭喜您，成功开通推广经理！");
                } else if (totalFee == 500) {
                    //VIP
                    user.setVipLevel(3);
                    user.setAdvCount(30);
                    user.setPhCount(500);
                    msg.setContent("恭喜您，成功开通VIP会员！");
                } else if (totalFee == 1000) {
                    //SVIP
                    user.setVipLevel(4);
                    user.setAdvCount(50);
                    user.setPhCount(1000);
                    msg.setContent("恭喜您，成功开通SVIP会员！");
                } else if (totalFee == 1800) {
                    //SSVIP
                    user.setVipLevel(5);
                    user.setAdvCount(0);
                    user.setPhCount(0);
                    msg.setContent("恭喜您，成功开通SSVIP会员！");
                }
                userService.updateByOpenid(user);

                msgService.save(msg);

                //加推广金钱
                List<Recommend> recommends = recommendService.getByApAll(userId);

                BigDecimal bigDecimal = null;
                BigDecimal bigDecimalt1 = new BigDecimal("0.45");

                BigDecimal bigDecimalt2 = new BigDecimal("0.25");
                for (Recommend recommend : recommends) {
                    if (totalFee == 99) {
                        //推广经理
                        BigDecimal bigDecimaj = new BigDecimal("99");
                        if (recommend.getRecommendType().equals("1")) {
                            bigDecimal = bigDecimaj.multiply(bigDecimalt1);

                        } else if (recommend.getRecommendType().equals("2")) {
                            bigDecimal = bigDecimaj.multiply(bigDecimalt2);
                        }
                    } else if (totalFee == 500) {
                        //VIP
                        BigDecimal bigDecimaV = new BigDecimal("500");
                        if (recommend.getRecommendType().equals("1")) {
                            bigDecimal = bigDecimaV.multiply(bigDecimalt1);

                        } else if (recommend.getRecommendType().equals("2")) {
                            bigDecimal = bigDecimaV.multiply(bigDecimalt2);

                        }
                    } else if (totalFee == 1000) {
                        //SVIP
                        BigDecimal bigDecimaSV = new BigDecimal("1000");
                        if (recommend.getRecommendType().equals("1")) {
                            bigDecimal = bigDecimaSV.multiply(bigDecimalt1);

                        } else if (recommend.getRecommendType().equals("2")) {
                            bigDecimal = bigDecimaSV.multiply(bigDecimalt2);

                        }
                    } else if (totalFee == 1800) {
                        //SSVIP
                        BigDecimal bigDecimaSSV = new BigDecimal("1800");
                        if (recommend.getRecommendType().equals("1")) {
                            bigDecimal = bigDecimaSSV.multiply(bigDecimalt1);

                        } else if (recommend.getRecommendType().equals("2")) {
                            bigDecimal = bigDecimaSSV.multiply(bigDecimalt2);

                        }
                    }
                    //更新推广人数和金额
                    WUser entity = new WUser();
                    entity.setId(recommend.getReferrerUserId());
                    entity.setBalance(bigDecimal);
                    userService.updateBaAdd(entity);
                }
            }

        }
        //通知微信支付系统接收到信息
        String str = "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg> </xml>";
        writer.write(str);
        writer.flush();
    }

    @RequestMapping(value = "getMsgList")
    public String getMsgList(String userId, HttpServletResponse response) {
        List<Msg> msgList = new ArrayList<>();
        if (StringUtils.isNoneBlank(userId)) {
            msgList = msgService.findList(new Msg(userId));
        }
        return renderString(response, msgList);
    }

    @RequestMapping(value = "getTeam")
    public String getTeam(String userId, HttpServletResponse response) {
        List<WUser> users = recommendService.getTeam(userId);
        return renderString(response, users);
    }

}
