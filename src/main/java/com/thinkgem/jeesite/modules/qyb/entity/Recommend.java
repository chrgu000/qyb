package com.thinkgem.jeesite.modules.qyb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Recommend extends DataEntity<Recommend> {

  private String referrerUserId;
  private String applicantUserId;
  private String recommendType;

  public Recommend() {
  }

  public Recommend(String referrerUserId, String applicantUserId, String recommendType) {
    this.referrerUserId = referrerUserId;
    this.applicantUserId = applicantUserId;
    this.recommendType = recommendType;
  }

  public String getReferrerUserId() {
    return referrerUserId;
  }

  public void setReferrerUserId(String referrerUserId) {
    this.referrerUserId = referrerUserId;
  }

  public String getApplicantUserId() {
    return applicantUserId;
  }

  public void setApplicantUserId(String applicantUserId) {
    this.applicantUserId = applicantUserId;
  }

  public String getRecommendType() {
    return recommendType;
  }

  public void setRecommendType(String recommendType) {
    this.recommendType = recommendType;
  }
}
