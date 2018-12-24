package com.thinkgem.jeesite.modules.qyb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Adver extends DataEntity<Adver> {
  private String photo;

  public Adver(String id, String photo) {
    this.id = id;
    this.photo = photo;
  }

  public Adver() {

  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}
