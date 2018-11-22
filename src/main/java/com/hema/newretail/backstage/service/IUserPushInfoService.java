package com.hema.newretail.backstage.service;

import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.PushInfoData;
import com.hema.newretail.backstage.entry.UserFormIdData;

import java.util.List;
import java.util.Map;

public interface IUserPushInfoService {
    Response findAll(Map<String,Object> params);
    PushInfoData findOne(String id);
    void save(PushInfoData data);
    void saveFormId(UserFormIdData data);
    UserFormIdData findOneByOpenId(String openId);
    String getAccessToken();
    String sendTemplate(String touser, String formId, String[] fillData);
    void deleteFormIdData(String formId);
}
