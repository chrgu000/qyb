package com.thinkgem.jeesite.common.fastweixin.api.response;

public class SnsTokenResponse extends GetTokenResponse {

  private String openid;
  private String scope;
  private String session_key;
  private String unionid;

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getSession_key() {
    return session_key;
  }

  public void setSession_key(String session_key) {
    this.session_key = session_key;
  }

  public String getUnionid() {
    return unionid;
  }

  public void setUnionid(String unionid) {
    this.unionid = unionid;
  }
}
