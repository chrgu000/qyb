package com.thinkgem.jeesite.modules.qyb.web;


import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.qyb.entity.ApplyCash;
import com.thinkgem.jeesite.modules.qyb.entity.WUser;
import com.thinkgem.jeesite.modules.qyb.service.ApplyService;
import com.thinkgem.jeesite.modules.qyb.service.WUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "${adminPath}/qyb/apply")
public class ApplyController extends BaseController {

  @Resource
  private ApplyService applyService;
  @Resource
  private WUserService userService;

  @RequestMapping(value = "form")
  public String form(ApplyCash applyCash) {
    return "modules/qyb/userForm";
  }

  @ModelAttribute("applyCash")
  public ApplyCash get(@RequestParam(required = false) String id, Model model) {
    if (StringUtils.isNoneBlank(id)) {
      return applyService.get(id);
    } else {
      return new ApplyCash();
    }
  }

  @RequestMapping(value = "list")
  public String list(HttpServletResponse response, HttpServletRequest request, ApplyCash applyCash, Model model) {
    Page<ApplyCash> page = applyService.findPage(new Page<>(request, response), applyCash);
    model.addAttribute("page", page);
    model.addAttribute("applyCash", applyCash);
    return "modules/qyb/applyList";
  }

  @RequestMapping(value = "updateSt")
  public String updateLevel(Model model, String id,String userId) {
    applyService.updateSt(id);
    userService.updateBal(userId);
    addMessage(model, "操作成功！");
    return "redirect:" + adminPath + "/qyb/apply/list?id=" + id;
  }
}
