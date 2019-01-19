package com.thinkgem.jeesite.modules.qyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qyb.entity.Recommend;
import com.thinkgem.jeesite.modules.qyb.entity.WUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface RecommendDAO extends CrudDao<Recommend> {

  Recommend getByAp(String refId);

  List<Recommend> getByApAll(String refId);

  List<WUser> getTeam(@Param("userId") String userId);

  int updateAp(@Param("userId") String userId );

}
