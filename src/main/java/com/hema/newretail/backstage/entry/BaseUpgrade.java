package com.hema.newretail.backstage.entry;

import com.hema.newretail.backstage.model.common.BasePage;

import java.util.Date;

public class BaseUpgrade extends BasePage {
    private Long id;

    private Integer upgradeType;

    private String iosVersion;

    private String androidVersion;

    private String apkUrl;

    private Integer status;

    private String upgradeDesc;

    private Integer isDeleted;

    private Date gmtCreate;

    private Date gmtModify;

    private String createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(Integer upgradeType) {
        this.upgradeType = upgradeType;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public void setIosVersion(String iosVersion) {
        this.iosVersion = iosVersion == null ? null : iosVersion.trim();
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion == null ? null : androidVersion.trim();
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl == null ? null : apkUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpgradeDesc() {
        return upgradeDesc;
    }

    public void setUpgradeDesc(String upgradeDesc) {
        this.upgradeDesc = upgradeDesc == null ? null : upgradeDesc.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}