package com.thinkgem.jeesite.modules.qyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qyb.entity.ApplyCash;

@MyBatisDao
public interface ApplyCaseDAO extends CrudDao<ApplyCash> {


   ApplyCash selectStatus(String  userId);

  int updateSt(String id);


}
