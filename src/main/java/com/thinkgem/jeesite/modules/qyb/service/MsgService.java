package com.thinkgem.jeesite.modules.qyb.service;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qyb.dao.MsgDao;
import com.thinkgem.jeesite.modules.qyb.entity.Msg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MsgService extends CrudService<MsgDao, Msg> {

}
