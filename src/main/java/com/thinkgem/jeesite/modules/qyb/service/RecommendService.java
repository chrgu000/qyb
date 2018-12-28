package com.thinkgem.jeesite.modules.qyb.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qyb.dao.RecommendDAO;
import com.thinkgem.jeesite.modules.qyb.entity.Recommend;

import java.util.List;

public class RecommendService extends CrudService<RecommendDAO, Recommend> {

  public Recommend getByAp(String refId) {
    return dao.getByAp(refId);
  }

  public List<Recommend> getByApAll(String refId) {
    return dao.getByApAll(refId);
  }
}
