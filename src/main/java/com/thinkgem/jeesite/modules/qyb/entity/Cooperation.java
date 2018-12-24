package com.thinkgem.jeesite.modules.qyb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * w_cooperation
 *
 * @author
 */
public class Cooperation extends DataEntity<Cooperation> {

  private static final long serialVersionUID = 1L;
  /**
   * 合作地区
   */
  private String area;
  /**
   * 所属公司
   */
  private String companyId;

  /**
   * 需要资源
   */
  private String needResource;
  /**
   * 提供资源
   */
  private String offerResource;
  /**
   * 1.待审核2.审核通过
   */
  private Integer status;
  /**
   * 标题
   */
  private String title;
  /**
   * 发布用户编号
   */
  private String userId;

  private Company company;
  private WUser user;
  /**
   * 合作方式
   */
  private String way;

  private Integer views;


  private Date beginDate;		// 开始日期
  private Date endDate;		// 结束日期


  public Date getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    Cooperation other = (Cooperation) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
        && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
        && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
        && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
        && (this.getWay() == null ? other.getWay() == null : this.getWay().equals(other.getWay()))
        && (this.getOfferResource() == null ? other.getOfferResource() == null : this.getOfferResource().equals(other.getOfferResource()))
        && (this.getNeedResource() == null ? other.getNeedResource() == null : this.getNeedResource().equals(other.getNeedResource()))
        && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getWay() {
    return way;
  }

  public void setWay(String way) {
    this.way = way;
  }

  public String getOfferResource() {
    return offerResource;
  }

  public void setOfferResource(String offerResource) {
    this.offerResource = offerResource;
  }

  public String getNeedResource() {
    return needResource;
  }

  public void setNeedResource(String needResource) {
    this.needResource = needResource;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", userId=").append(userId);
    sb.append(", companyId=").append(companyId);
    sb.append(", title=").append(title);
    sb.append(", area=").append(area);
    sb.append(", way=").append(way);
    sb.append(", offerResource=").append(offerResource);
    sb.append(", needResource=").append(needResource);
    sb.append(", status=").append(status);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
    result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
    result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
    result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
    result = prime * result + ((getWay() == null) ? 0 : getWay().hashCode());
    result = prime * result + ((getOfferResource() == null) ? 0 : getOfferResource().hashCode());
    result = prime * result + ((getNeedResource() == null) ? 0 : getNeedResource().hashCode());
    result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
    return result;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public WUser getUser() {
    return user;
  }

  public void setUser(WUser user) {
    this.user = user;
  }
}