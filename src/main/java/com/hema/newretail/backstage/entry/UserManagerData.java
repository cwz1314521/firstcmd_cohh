package com.hema.newretail.backstage.entry;

import com.hema.newretail.backstage.entry.usermanger.Count;
import com.hema.newretail.backstage.entry.usermanger.Years;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 会员信息展示类
 */
public class UserManagerData {
    private String id;//用户主键id

    private String openId;

    private String nickname;//昵称

    private String phoneNumber;//电话

    private String headPortrait; //头像

    private String tag; //标签

    private Double averageConsumer;//次消费均值

    private String status;//0解冻1冻结

    private List<Years> years;

    private Date registrationDate;//注册时间

    private Date recentlyDate;//最近一次消费时间

    private String province;//省

    private String city;//市

    private String area;//区

    private Count thisWeekTime; //消费次数

    private Count thisWeekRental;//周消费总额

    private Count thisWeekAverage;//次消费均值

    private Double weekRental;//本周消费总额

    private Integer weekTime;//本周消费次数

    private List<String> tags;

    private String registrationDateStr;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(Integer weekTime) {
        this.weekTime = weekTime;
    }

    public Double getWeekRental() {
        return weekRental;
    }

    public void setWeekRental(Double weekRental) {
        this.weekRental = weekRental;
    }

    public Count getThisWeekTime() {
        return thisWeekTime;
    }

    public void setThisWeekTime(Count thisWeekTime) {
        this.thisWeekTime = thisWeekTime;
    }

    public Count getThisWeekRental() {
        return thisWeekRental;
    }

    public void setThisWeekRental(Count thisWeekRental) {
        this.thisWeekRental = thisWeekRental;
    }

    public Count getThisWeekAverage() {
        return thisWeekAverage;
    }

    public void setThisWeekAverage(Count thisWeekAverage) {
        this.thisWeekAverage = thisWeekAverage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Double getAverageConsumer() {
        return averageConsumer;
    }

    public void setAverageConsumer(Double averageConsumer) {
        this.averageConsumer = averageConsumer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Years> getYears() {
        return years;
    }

    public void setYears(List<Years> years) {
        this.years = years;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRecentlyDate() {
        return recentlyDate;
    }

    public void setRecentlyDate(Date recentlyDate) {
        this.recentlyDate = recentlyDate;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getRegistrationDateStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(this.registrationDate == null ? new Date():this.registrationDate);
    }

    public void setRegistrationDateStr(String registrationDateStr) {
        this.registrationDateStr = registrationDateStr;
    }

}
