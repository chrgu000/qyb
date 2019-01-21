package com.thinkgem.jeesite.modules.qyb.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qyb.dao.ApplyCaseDAO;
import com.thinkgem.jeesite.modules.qyb.entity.ApplyCash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ApplyService extends CrudService<ApplyCaseDAO, ApplyCash> {


  public ApplyCash selectStatus(String  userId){
    return dao.selectStatus(userId);
  }
  @Transactional(readOnly = false)
  public int updateSt(String id){
    return dao.updateSt(id);
  }

}
