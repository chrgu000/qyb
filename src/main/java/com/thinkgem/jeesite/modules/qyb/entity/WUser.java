package com.thinkgem.jeesite.modules.qyb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * w_user
 *
 * @author
 */
public class WUser extends DataEntity<WUser> {


  private static final long serialVersionUID = 1L;
  /**
   * 可发广告数量
   */
  private Integer advCount;
  /**
   * 区县
   */
  private String area;
  /**
   * 头像
   */
  private String avatar;
  /**
   * 剩余现金
   */
  private BigDecimal balance;
  /**
   * 城市
   */
  private String city;
  private Integer companion;
  private Company company;
  /**
   * 所属公司
   */
  private String companyId;
  /**
   * 国家
   */
  private String country;
  /**
   * 手机号码
   */
  private String mobile;
  /**
   * 姓名
   */
  private String name;
  private String openid;
  /**
   * 查看电话数量
   */
  private Integer phCount;
  private String position;
  /**
   * 省份
   */
  private String province;
  /**
   * 性别
   */
  private Integer sex;
  /**
   * 1.普通用户2.推广经理3.vip4.svip5.ssvip
   */
  private Integer vipLevel;
  private String wechatAccount;

  public WUser() {
  }

  public WUser(String openid) {
    this.openid = openid;
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
    WUser other = (WUser) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
        && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
        && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
        && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
        && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
        && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
        && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
        && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()));
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", companyId=").append(companyId);
    sb.append(", vipLevel=").append(vipLevel);
    sb.append(", name=").append(name);
    sb.append(", mobile=").append(mobile);
    sb.append(", sex=").append(sex);
    sb.append(", avatar=").append(avatar);
    sb.append(", country=").append(country);
    sb.append(", province=").append(province);
    sb.append(", city=").append(city);
    sb.append(", area=").append(area);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
    result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
    result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
    result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
    result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
    result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
    return result;
  }

  public Integer getAdvCount() {
    return advCount;
  }

  public void setAdvCount(Integer advCount) {
    this.advCount = advCount;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Integer getCompanion() {
    return companion;
  }

  public void setCompanion(Integer companion) {
    this.companion = companion;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public Integer getPhCount() {
    return phCount;
  }

  public void setPhCount(Integer phCount) {
    this.phCount = phCount;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public Integer getVipLevel() {
    return vipLevel;
  }

  public void setVipLevel(Integer vipLevel) {
    this.vipLevel = vipLevel;
  }

  public String getWechatAccount() {
    return wechatAccount;
  }

  public void setWechatAccount(String wechatAccount) {
    this.wechatAccount = wechatAccount;
  }
}