package com.thinkgem.jeesite.modules.qyb.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qyb.dao.CooperationDAO;
import com.thinkgem.jeesite.modules.qyb.entity.Adver;
import com.thinkgem.jeesite.modules.qyb.entity.Cooperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CooperationService extends CrudService<CooperationDAO, Cooperation> {

  public List<Cooperation> findList(Cooperation entity) {
    return dao.findList(entity);
  }

  public Adver getAdver() {
    return dao.getAdver();
  }

  public Cooperation getDetail(String id) {
    return dao.getDetail(id);
  }

  @Transactional(readOnly = false)
  public void udpateAdver(Adver adver) {
    dao.udpateAdver(adver);
  }

  @Transactional(readOnly = false)
  public void updateStatus(Cooperation entity) {
    dao.updateStatus(entity);
  }



  public List<Cooperation >findCotList(String cpyId){
    return dao.findCotList(cpyId);
  }

}
