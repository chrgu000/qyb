package com.thinkgem.jeesite.modules.qyb.web;


import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.qyb.entity.WUser;
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
@RequestMapping(value = "${adminPath}/qyb/wUser")
public class WUserController extends BaseController {

  @Resource
  private WUserService wUserService;

  @RequestMapping(value = "form")
  public String form(WUser wUser) {
    return "modules/qyb/userForm";
  }

  @ModelAttribute("user")
  public WUser get(@RequestParam(required = false) String id, Model model) {
    if (StringUtils.isNoneBlank(id)) {
      return  wUserService.get(id);
    } else {
      return new WUser();
    }
  }

  @RequestMapping(value = "list")
  public String list(HttpServletResponse response, HttpServletRequest request, WUser user, Model model) {
    Page<WUser> page = wUserService.findPage(new Page<>(request, response), user);
    model.addAttribute("page", page);
    return "modules/qyb/userList";
  }

  @RequestMapping(value = "updateLevel")
  public String updateLevel(Model model, WUser wUser) {
    wUserService.updateLevel(wUser);

    if(wUser.getVipLevel()==1){

    }

    addMessage(model, "修改成功！");
    return "redirect:" + adminPath + "/qyb/wUser/list?id="+wUser.getId();
  }
}
