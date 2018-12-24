package com.thinkgem.jeesite.modules.qyb.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qyb.dao.UserDAO;
import com.thinkgem.jeesite.modules.qyb.entity.WUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WUserService extends CrudService<UserDAO, WUser> {

  public List<WUser> findByCpy(String cpyId) {
    return dao.findByCpy(cpyId);
  }

  public WUser getByOpenid(String openid) {
    return dao.getByOpenid(openid);
  }

  @Transactional(readOnly = false)
  public int updateLevel(WUser entity) {
    return dao.updateLevel(entity);
  }

}
