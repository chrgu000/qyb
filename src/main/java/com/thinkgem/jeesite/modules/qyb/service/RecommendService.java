package com.thinkgem.jeesite.modules.qyb.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qyb.dao.RecommendDAO;
import com.thinkgem.jeesite.modules.qyb.entity.Recommend;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class RecommendService extends CrudService<RecommendDAO, Recommend> {

  public Recommend getByAp(String refId) {
    return dao.getByAp(refId);
  }

  public List<Recommend> getByApAll(String refId) {
    return dao.getByApAll(refId);
  }
}
