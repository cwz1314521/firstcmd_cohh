package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.erp.InStoreDBCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStoreRecordListDBCondition;
import com.hema.newretail.backstage.common.queryparam.erp.StockManageCondition;
import com.hema.newretail.backstage.entry.erp.ErpOrderQrcodeEntry;
import com.hema.newretail.backstage.model.erp.InStoreListBo;
import com.hema.newretail.backstage.model.erp.InStoreTodayBo;
import com.hema.newretail.backstage.model.erp.InstoreRecordBo;
import com.hema.newretail.backstage.model.erp.StockManageBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErpOrderQrcodeMapper {


    int updateByQrcodeCodeOut(String qrcodeCode);

    int updateDelete(Long id);


    List<InstoreRecordBo> selectRecordMap(InStoreRecordListDBCondition inStoreRecordListDBCondition);

    List<InStoreTodayBo> selectOutStoreTodayMap();

    List<InStoreTodayBo> selectInStoreTodayMap();

    List<InStoreTodayBo> selectOutStoreNowMap();

    List<InStoreTodayBo> selectInStoreNowMap();

    List<InStoreListBo> selectBaseOutStoreListMap();

    List<InStoreListBo> selectBaseInStoreListMap();

    int updateByQrcodeCode(String qrcodeCode);

    int deleteByPrimaryKey(Long id);

    int insert(ErpOrderQrcodeEntry record);

    int insertSelective(ErpOrderQrcodeEntry record);

    ErpOrderQrcodeEntry selectByPrimaryKey(Long id);

    ErpOrderQrcodeEntry selectByQrcodeCode(String qrcodeCode);

    ErpOrderQrcodeEntry selectByQrcodeCodeOut(String qrcodeCode);

    int updateByPrimaryKeySelective(ErpOrderQrcodeEntry record);

    int updateByOutStoreDBCondition(InStoreDBCondition inStoreDBCondition);

    int updateByInStoreDBCondition(InStoreDBCondition inStoreDBCondition);

    int updateByPrimaryKey(ErpOrderQrcodeEntry record);

    /**
     * 查询分公司库存管理列表
     *
     * @param data     参数
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return list
     */
    List<StockManageBo> selectCompanyStockList(@Param("data") StockManageCondition data, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}