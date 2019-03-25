package com.hema.newretail.backstage.service.user;

import com.hema.newretail.backstage.common.queryparam.user.BusiComplaintListCondition;
import com.hema.newretail.backstage.common.utils.Response;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName BusiComplaintService
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/3/23 11:35
 * @Version 1.0
 **/


public interface BusiComplaintService {

    /**
     *
     * 功能描述:客诉记录列表
     *
     * @param  condition
     * @return
     * @author  cwz
     * @date  2019/3/23 11:09
     */
    Response list(BusiComplaintListCondition condition)throws Exception;

    /**
     *
     * 功能描述:客诉记录导出
     *
     * @param  condition
     * @return
     * @author  cwz
     * @date  2019/3/23 11:09
     */
    Response excel(BusiComplaintListCondition condition, HttpServletResponse response)throws Exception;

    /**
     *
     * 功能描述:客诉记录编辑
     *
     * @param  condition
     * @return
     * @author  cwz
     * @date  2019/3/23 11:09
     */
    Response edit(BusiComplaintListCondition condition);
}
