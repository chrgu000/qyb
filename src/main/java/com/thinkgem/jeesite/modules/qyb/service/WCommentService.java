package com.thinkgem.jeesite.modules.qyb.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qyb.dao.CommentDAO;
import com.thinkgem.jeesite.modules.qyb.entity.WComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WCommentService extends CrudService<CommentDAO, WComment> {
  public  List<WComment> getByCopId(String copId){
    return dao.getByCopId(copId);
  }
}
