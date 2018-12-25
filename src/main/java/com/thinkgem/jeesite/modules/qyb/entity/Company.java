package com.thinkgem.jeesite.modules.qyb.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * w_company
 * @author 
 */
public class Company extends DataEntity<Company> {
    /**
     * 公司名称
     */
    private String name;


    /**
     * 公司logo
     */
    private String logo;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 行业
     */
    private String industry;

    /**
     * 介绍
     */
    private String introdction;

    /**
     * 网址
     */
    private String site;

    private Integer userCount;
    private Integer cooperationCount;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getCooperationCount() {
        return cooperationCount;
    }

    public void setCooperationCount(Integer cooperationCount) {
        this.cooperationCount = cooperationCount;
    }


    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIntrodction() {
        return introdction;
    }

    public void setIntrodction(String introdction) {
        this.introdction = introdction;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
        Company other = (Company) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry()))
            && (this.getIntrodction() == null ? other.getIntrodction() == null : this.getIntrodction().equals(other.getIntrodction()))
            && (this.getSite() == null ? other.getSite() == null : this.getSite().equals(other.getSite()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        result = prime * result + ((getIntrodction() == null) ? 0 : getIntrodction().hashCode());
        result = prime * result + ((getSite() == null) ? 0 : getSite().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", logo=").append(logo);
        sb.append(", city=").append(city);
        sb.append(", industry=").append(industry);
        sb.append(", introdction=").append(introdction);
        sb.append(", site=").append(site);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}