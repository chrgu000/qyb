package com.thinkgem.jeesite.modules.qyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.modules.qyb.entity.Recommend;

import java.util.List;

public interface RecommendDAO extends CrudDao<Recommend> {

  Recommend getByAp(String refId);

  List<Recommend> getByApAll(String refId);

}
