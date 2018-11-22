package com.hema.newretail.backstage.service;

import com.hema.newretail.backstage.common.queryparam.usermanagementparameter.UserManaCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.UserManagerData;

import java.util.List;
import java.util.Map;

public interface IUserManagerService {

    UserManagerData queryOneById(String id);

    Response getUserConsumptionsByOpenId(Map<String,Object> paramperters);

    boolean updateStatusById(String id);

    List<UserManagerData> findAll(UserManaCondition userManaCondition);

    List<UserManagerData> queryAllByIds(List<String> ids);

}
