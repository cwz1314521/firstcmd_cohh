package com.hema.newretail.backstage.service.index;

import com.hema.newretail.backstage.common.queryparam.index.*;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * @Department 新零售
 * @ClassName ArticleService
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/26 10:48
 * @Version 1.0
 **/

public interface ArticleService {


    /**
     *
     * 功能描述: 首页文章列表
     *
     * @param condition
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    Response list(ArticleListCondition condition);


    /**
     *
     * 功能描述: 首页文章添加
     *
     * @param condition
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    Response add(ArticleAddCondition condition) throws Exception;

    /**
     *
     * 功能描述: 首页文章修改
     *
     * @param condition
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    Response edit(ArticleEditCondition condition) throws Exception;

    /**
     *
     * 功能描述: 首页文章删除
     *
     * @param   condition
     * @return
     * @author  cwz
     * @date  2019/2/25 15:13
     */
    Response delete(ArticleDeleteCondition condition);
}
