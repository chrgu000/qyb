package com.thinkgem.jeesite.modules.qyb.web;

import com.thinkgem.jeesite.common.utils.BaseResponse;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.qyb.entity.Adver;
import com.thinkgem.jeesite.modules.qyb.service.CooperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/qyb/adver")
public class AdverController extends BaseController {

  @Resource
  private CooperationService cooperationService;

  @RequestMapping(value = "adverPage")
  public String adverPage(Model model) {
    Adver adver = cooperationService.getAdver();
    model.addAttribute("adver", adver);
    return "/modules/qyb/adverForm";
  }

  @ModelAttribute("adver")
  public Adver get() {
    //if (StringUtils.isNotBlank(id)) {
    return cooperationService.getAdver();
    //} else {
    //return new Cooperation();
    // }
  }

  @RequestMapping(value = "udpateAdver")
  public String udpateAdver(HttpServletResponse response, Adver adver,Model model) {
    cooperationService.udpateAdver(adver);

    addMessage(model,"保存成功！");
    return "redirect:" + adminPath + "/qyb/adver/adverPage";
  }

}
