package com.hema.newretail.backstage.service.basecompany;

import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonAddCondition;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonDeleteCondition;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonEditCondition;
import com.hema.newretail.backstage.common.queryparam.basecpmpany.InstallPersonListCondition;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @Department 新零售
 * @ClassName InstallationPersonnelService
 * @Description 安装人员管理service
 * @Author ---CWZ
 * @Date 2018/12/7 16:07
 * @Version 1.0
 **/
public interface InstallationPersonnelService {

    /**
     *
     * 功能描述: 安装人员列表以及条件检索接口
     *
     * @param: InstallPersonListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    Response list(InstallPersonListCondition installPersonListCondition);
    /**
     *
     * 功能描述:安装人员添加接口
     *
     * @param: InstallPersonAddCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    Response add(HttpServletRequest request,InstallPersonAddCondition installPersonAddCondition);

    /**
     *
     * 功能描述:安装人员修改接口
     *
     * @param: InstallPersonEditCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */
    Response edit(InstallPersonEditCondition installPersonAddCondition);

    /**
     *
     * 功能描述:安装人员删除接口
     *
     * @param: InstallPersonDeleteCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */

    Response delete(InstallPersonDeleteCondition installPersonAddCondition);

    /**
     *
     * 功能描述:安装人员重置密码接口
     *
     * @param: InstallPersonDeleteCondition
     * @return: success
     * @author: cwz
     * @date: 2018/12/7 15:18
     */

    Response reset(InstallPersonDeleteCondition installPersonAddCondition);

}
