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
@RequestMapping(value = "${adminPath}/qyb/apply")
public class ApplyController extends BaseController {

  @Resource
  private WUserService wUserService;

  @RequestMapping(value = "form")
  public String form(WUser wUser) {
    return "modules/qyb/userForm";
  }

  @ModelAttribute("user")
  public WUser get(@RequestParam(required = false) String id, Model model) {
    if (StringUtils.isNoneBlank(id)) {
      return wUserService.get(id);
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
  public String updateLevel(Model model, WUser user) {
    if (user.getVipLevel() == 1) {
      user.setVipLevel(1);
      user.setAdvCount(0);
      user.setPhCount(0);
    } else if (user.getVipLevel() == 2) {
      //推广经理
      user.setVipLevel(2);
      user.setAdvCount(0);
      user.setPhCount(0);
    } else if (user.getVipLevel() == 3) {
      //VIP
      user.setVipLevel(3);
      user.setAdvCount(30);
      user.setPhCount(380);
    } else if (user.getVipLevel() == 4) {
      //SVIP
      user.setVipLevel(4);
      user.setAdvCount(50);
      user.setPhCount(1000);
    } else if (user.getVipLevel() == 5) {
      //SSVIP
      user.setVipLevel(5);
      user.setAdvCount(0);
      user.setPhCount(0);
    }
    wUserService.updateByOpenid(user);
    addMessage(model, "修改成功！");
    return "redirect:" + adminPath + "/qyb/wUser/list?id=" + user.getId();
  }
}
