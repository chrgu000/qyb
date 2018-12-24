package com.thinkgem.jeesite.modules.qyb.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.qyb.entity.Cooperation;
import com.thinkgem.jeesite.modules.qyb.service.CooperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/qyb/cooperation")
public class CooperationController extends BaseController {

  @Resource
  private CooperationService cooperationService;

  @ModelAttribute("cooperation")
  public Cooperation get(@RequestParam(required = false) String id) {
    if (StringUtils.isNotBlank(id)) {
      return cooperationService.get(id);
    } else {
      return new Cooperation();
    }
  }

  @RequestMapping(value = {"list", ""})
  public String list(HttpServletRequest request, HttpServletResponse response, Cooperation cooperation, Model model) {
    Page<Cooperation> page = cooperationService.findPage(new Page<>(request, response), cooperation);
    model.addAttribute("page", page);
    return "modules/qyb/cooperationList";
  }

  @RequestMapping(value = "updateStatus")
  public String updateStatus( Model model, Cooperation cooperation) {
    cooperationService.updateStatus(cooperation);
    addMessage(model, "更新成功！");
    return "redirect:" + adminPath + "/qyb/cooperation/list?repage";
  }

  @RequestMapping(value = "delete")
  public String delete(Cooperation cooperation,Model model){
    cooperationService.delete(cooperation);
    addMessage(model, "删除成功！");
    return "redirect:" + adminPath + "/qyb/cooperation/list?repage";
  }


}
