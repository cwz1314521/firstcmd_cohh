package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.index.ArticleListCondition;
import com.hema.newretail.backstage.entry.BaseIndexArticleEntry;
/**
 *
 * 功能描述:
 *
 * @param
 * @return
 * @author  cwz
 * @date  2019/2/26 12:00
 */
import java.util.List;
import java.util.Map;

public interface BaseIndexArticleMapper {
    /**
     *
     * 功能描述:
     *
     * @param    id
     * @return  
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    int insert(BaseIndexArticleEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    int insertSelective(BaseIndexArticleEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   id
     * @return
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    BaseIndexArticleEntry selectByPrimaryKey(Long id);

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    List<BaseIndexArticleEntry> list(ArticleListCondition condition);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    int updateByPrimaryKeySelective(BaseIndexArticleEntry record);

    /**
     *
     * 功能描述:
     *
     * @param   record
     * @return
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    int updateByPrimaryKeyWithBLOBs(BaseIndexArticleEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  record
     * @return
     * @author  cwz
     * @date  2019/2/26 12:00
     */
    int updateByPrimaryKey(BaseIndexArticleEntry record);

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/2/26 15:42
     */
    int updateDelete(Long id);


    /**
     *
     * 功能描述:
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/2/26 15:50
     */
    List<Map> selectAllArticleType();
}