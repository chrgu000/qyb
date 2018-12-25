package com.thinkgem.jeesite.modules.qyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qyb.entity.Adver;
import com.thinkgem.jeesite.modules.qyb.entity.Cooperation;

import java.util.List;

/**
 * WCooperationDAO继承基类
 */
@MyBatisDao
public interface CooperationDAO extends CrudDao<Cooperation> {

  List<Cooperation> findCotList(String cpyId);

  Adver getAdver();

  Cooperation getDetail(String id);

  void udpateAdver(Adver adver);

  void updateStatus(Cooperation entity);

  int updateViews(String id);

  List<Cooperation> findByUser(String userId);

}