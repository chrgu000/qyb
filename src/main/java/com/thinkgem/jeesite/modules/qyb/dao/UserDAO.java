package com.thinkgem.jeesite.modules.qyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qyb.entity.WUser;

import java.util.List;

@MyBatisDao
public interface UserDAO extends CrudDao<WUser> {

  WUser getByInfo(WUser entity);

  int updateLevel(WUser entity);


  List<WUser> findByCpy(String cpyId);


  Integer getAdvCount(String id);


  int subAdv(String id);




  Integer getPhCount(String id);


  int subPh(String id);


  int updateByOpenid(WUser user);


  String getUserId(String openid);

  int updateBaAdd(WUser user);

  int updateCompany(WUser user);


  int updateBal(String userId);
}
