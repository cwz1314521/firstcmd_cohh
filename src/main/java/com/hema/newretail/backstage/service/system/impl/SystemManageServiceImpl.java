package com.hema.newretail.backstage.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.system.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.StringUtil;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.dao.BaseMachineScreenPicMapper;
import com.hema.newretail.backstage.dao.BaseUpgradeMapper;
import com.hema.newretail.backstage.dao.ZoneBaseMapper;
import com.hema.newretail.backstage.entry.BaseMachineScreenPicEntry;
import com.hema.newretail.backstage.entry.BaseUpgrade;
import com.hema.newretail.backstage.entry.ZoneBase;
import com.hema.newretail.backstage.model.system.PicListBo;
import com.hema.newretail.backstage.service.system.SystemManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName SystemManageServiceImpl
 * @Description 系统管理相关ServiceImpl
 * @Author ---CWZ
 * @Date 2018/12/24 13:55
 * @Version 1.0
 **/


@Service
public class SystemManageServiceImpl implements SystemManageService {

    @Autowired
    private BaseMachineScreenPicMapper baseMachineScreenPicMapper;
	@Autowired
	private BaseUpgradeMapper baseUpgradeMapper;
    @Autowired
    private ZoneBaseMapper zoneBaseMapper;
    private static final String SDFE = "yyyy-MM-dd";
    private static final String ZEROS = "0";
    private static final String ONES = "1";


    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    /**
     * 功能描述: 机器大屏广告列表
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    public Response machinePic(MachinePicListCondition condition) {
        logger.info("分页参数拼装");
        Page<PicListBo> page=PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        baseMachineScreenPicMapper.selectListMap(condition);
        return Response.success(page.getResult(),page.getTotal(),page.getPageSize(),page.getPageNum());
    }

    /**
     * 功能描述: 机器大屏广告add
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    @Transactional
    public Response machinePicAdd(MachinePicAddCondition condition) throws Exception{
        logger.info("参数类拼装");
        BaseMachineScreenPicEntry entry = new BaseMachineScreenPicEntry();
        entry.setSort(condition.getSort());
        entry.setStatus(condition.getStatus());
        entry.setPicUrl(condition.getPicUrl());
        entry.setInfo(condition.getInfo());
        entry.setGmtStart(TimeUtil.stringToDate(condition.getGmtStart(),SDFE));
        entry.setGmtEnd(TimeUtil.stringToDate(condition.getGmtEnd(),SDFE));
        entry.setGmtCreate(new Date());
        entry.setGmtModify(new Date());
        int insert = baseMachineScreenPicMapper.insert(entry);
        if(insert != 1){
            logger.error("存储失败");
            return Response.failure("操作失败");
        }
        UpdateByMachinePicCondition update = new UpdateByMachinePicCondition();
        if(ZEROS.equals(condition.getType())){
            update.setId(null);
            update.setType(null);
            update.setMId(entry.getId());
            zoneBaseMapper.updateByMachinePic(update);
        } else if(ONES.equals(condition.getType())){
            if(condition.getIds() != null && !"".equals(condition.getIds())){
                update.setType(condition.getType());
                update.setId(StringUtil.stringsToLong(condition.getIds()));
                update.setMId(entry.getId());
                zoneBaseMapper.updateByMachinePic(update);
            }

        }
        return Response.success();
    }

    /**
     * 功能描述: 机器大屏广告edit
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    @Transactional
    public Response machinePicEdit(MachinePicEditCondition condition) throws Exception{

        BaseMachineScreenPicEntry entry = new BaseMachineScreenPicEntry();
        logger.info("参数类拼装");
        entry.setId(condition.getId());
        entry.setSort(condition.getSort());
        entry.setStatus(condition.getStatus());
        entry.setPicUrl(condition.getPicUrl());
        entry.setInfo(condition.getInfo());
        entry.setGmtStart(TimeUtil.stringToDate(condition.getGmtStart(),SDFE));
        entry.setGmtEnd(TimeUtil.stringToDate(condition.getGmtEnd(),SDFE));
        entry.setGmtModify(new Date());
        int insert = baseMachineScreenPicMapper.updateByPrimaryKeySelective(entry);
        if(insert != 1){
            logger.error("存储失败");
            return Response.failure("操作失败");
        }
        List<ZoneBase> zoneBases = zoneBaseMapper.selectByPicId(entry.getId());
        for (ZoneBase z:zoneBases
                ) {
            z.setMachineScreenPicId(0L);
            zoneBaseMapper.updateByPrimaryKeySelective(z);
        }
        UpdateByMachinePicCondition update = new UpdateByMachinePicCondition();
        if(ZEROS.equals(condition.getType())){
            update.setId(null);
            update.setType(null);
            update.setMId(entry.getId());
            zoneBaseMapper.updateByMachinePic(update);
        } else if(ONES.equals(condition.getType())){
            if(condition.getIds() != null && !"".equals(condition.getIds())){
                update.setType(condition.getType());
                update.setMId(entry.getId());
                update.setId(StringUtil.stringsToLong(condition.getIds()));
                zoneBaseMapper.updateByMachinePic(update);
            }

        }
        return Response.success();
    }


    /**
     * 功能描述: 机器大屏广告delete
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    public Response machinePicDelete(IdCondition condition) {
        int count = zoneBaseMapper.selectCountByPic(condition.getId());
        if(count>0){
            logger.error("还绑定图片");
            return Response.failure("请先解绑片区，然后再删除");
        }else {
            int i = baseMachineScreenPicMapper.deleteByPrimaryKey(condition.getId());
            if(i == 1){
                return Response.success();
            }else {
                return Response.failure("操作失败");
            }
        }

    }

	@Override
	public Response baseUpgradeList(BaseUpgrade condition) {
		Page<Object> objects = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
		List<BaseUpgrade> list = baseUpgradeMapper.baseUpgradeList(condition);
		return Response.success(list,objects.getTotal(),condition.getPageSize(),condition.getPageNum());
	}

	@Override
	public Response baseUpgradeEdit(BaseUpgrade condition) {
		Long id = condition.getId();
		Date date = new Date();
		condition.setGmtModify(date);
		int re;
		if (null == id) {
			condition.setGmtCreate(date);
			  re = baseUpgradeMapper.insertSelective(condition);
		} else {
			re = baseUpgradeMapper.updateByPrimaryKeySelective(condition);
		}
		if(re<=0){
		    return Response.failure();
        }else{
            return Response.success();
        }
	}

    @Override
    public Response baseUpgradeDetail(BaseUpgrade condition) {
        Long id = condition.getId();
        BaseUpgrade baseUpgrade = baseUpgradeMapper.selectByPrimaryKey(id);
        return Response.success(baseUpgrade);
    }

    @Override
	public Response baseUpgradeDelete(BaseUpgrade condition) {
		int re = baseUpgradeMapper.deleteByPrimaryKey(condition.getId());
		if(re<=0){
		    return Response.failure();
        }else{
            return Response.success();
        }
	}

}
