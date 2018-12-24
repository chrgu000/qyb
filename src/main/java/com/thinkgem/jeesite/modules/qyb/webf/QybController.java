package com.thinkgem.jeesite.modules.qyb.webf;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.fastweixin.api.SessionApi;
import com.thinkgem.jeesite.common.fastweixin.api.config.ApiConfig;
import com.thinkgem.jeesite.common.fastweixin.api.response.SnsTokenResponse;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.BaseResponse;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.qyb.entity.*;
import com.thinkgem.jeesite.modules.qyb.service.CompanyService;
import com.thinkgem.jeesite.modules.qyb.service.CooperationService;
import com.thinkgem.jeesite.modules.qyb.service.WCommentService;
import com.thinkgem.jeesite.modules.qyb.service.WUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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


  @RequestMapping(value = {"company/list"})
  public String companyList(HttpServletRequest request, HttpServletResponse response, Company company) {
    Page<Company> page = companyService.findPage(new Page<>(request, response), company);
    return renderString(response, BaseResponse.success("success", page));
  }

  @RequestMapping(value = {"company/info"})
  public String companyInfo(HttpServletRequest request, HttpServletResponse response, String id) {
    Company company = companyService.get(id);
    return renderString(response, BaseResponse.success(company));
  }

  @RequestMapping(value = {"cooperation/detail"})
  public String coopDetail(Cooperation entity, HttpServletRequest request, HttpServletResponse response) {
    Cooperation cooperation = cooperationService.getDetail(entity.getId());
    return renderString(response, BaseResponse.success(cooperation));
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
          adverList.add(new Adver("1", "http://localhost:8181" + str));
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

  @RequestMapping(value = "user/info")
  public String getUserInfo(HttpServletResponse response, String openid) {
    if (StringUtils.isNoneBlank(openid)) {
      WUser user = userService.getByOpenid(openid);
      return renderString(response, BaseResponse.success(user));
    } else {
      return renderString(response, BaseResponse.error("参数错误!"));
    }

  }

  @RequestMapping(value = {"cooperation/list"})
  public String list(Cooperation entity, HttpServletRequest request, HttpServletResponse response) {
    Page<Cooperation> page = cooperationService.findPage(new Page<>(request, response), entity);
    return renderString(response, BaseResponse.success(page));
  }

  @RequestMapping(value = "cooperation/save", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
  public String save(HttpServletResponse response, @RequestBody Cooperation entity) {
    cooperationService.save(entity);
    return renderString(response, BaseResponse.success("发布成功"));
  }

  @RequestMapping(value = {"cooperation/findCotList"})
  public String findCotList(String cpyId,HttpServletResponse response){
    List<Cooperation> list = cooperationService.findCotList(cpyId);
    return renderString(response, BaseResponse.success(list));
  }


  @RequestMapping(value = {"getOpenId"})
  public String getOpenid(HttpServletResponse response){
    SessionApi sessionApi=new SessionApi(new ApiConfig("",""));
    String code="071fUVQd1kfk7z0fR7Nd19MdRd1fUVQt";
    SnsTokenResponse re= sessionApi.executeSGet("sns/jscode2session?appid=wxa2df920f1bbc2e1d&secret=SECRET&js_code=#&grant_type=authorization_code",code);
    return renderString(response,BaseResponse.success(re));
  }
}
