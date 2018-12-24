package com.thinkgem.jeesite.modules.qyb.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qyb.entity.WComment;

import java.util.List;

@MyBatisDao
public interface CommentDAO extends CrudDao<WComment> {

  List<WComment> getByCopId(String copId);

}
