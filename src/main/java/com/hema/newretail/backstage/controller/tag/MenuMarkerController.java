package com.hema.newretail.backstage.controller.tag;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerCondition;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerListCondition;
import com.hema.newretail.backstage.common.queryparam.tag.MenuMarkerSaveCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.validator.First;
import com.hema.newretail.backstage.common.validator.Second;
import com.hema.newretail.backstage.entry.MenuMarker;
import com.hema.newretail.backstage.entry.RefMenuMarker;
import com.hema.newretail.backstage.service.tag.MenuMarkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller.tag
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-05 16:28
 */
@Api(description = "饮品角标管理")
@RestController
@RequestMapping("/tag/marker")
@AutoLog
public class MenuMarkerController {
    @Autowired
    private MenuMarkerService menuMarkerService;

    @ApiOperation("查询饮品角标记录")
    @PostMapping("/list")
    public Response list(@RequestBody @Validated MenuMarkerCondition condition) {
        return menuMarkerService.queryList(condition);
    }

    @ApiOperation("添加饮品角标")
    @PostMapping("/add")
    public Response add(@RequestBody @Validated({First.class}) MenuMarker condition) {
        return menuMarkerService.insertData(condition);
    }

    @ApiOperation("编辑饮品角标")
    @PostMapping("/edit")
    public Response edit(@RequestBody @Validated({First.class, Second.class}) MenuMarker condition) {
        return menuMarkerService.updateData(condition);
    }

    @ApiOperation("删除饮品角标")
    @PostMapping("/delete")
    public Response delete(@RequestBody @Validated({Second.class}) MenuMarker condition) {
        return menuMarkerService.deleteData(condition);
    }

    @ApiOperation("查看角标关联的饮品")
    @PostMapping("/toSaveMarker")
    public Response toSaveMarker(@RequestBody @Validated MenuMarkerListCondition condition) {
        return menuMarkerService.toSaveMarkerData(condition);
    }

    @ApiOperation("关联饮品")
    @PostMapping("/saveMarker")
    public Response saveMarker(@RequestBody @Validated MenuMarkerSaveCondition condition) {
        return menuMarkerService.saveMarkerData(condition);
    }

    @ApiOperation("移除关联饮品")
    @PostMapping("/deleteMarker")
    public Response deleteMarker(@RequestBody @Validated({Second.class}) RefMenuMarker condition) {
        return menuMarkerService.deleteMarkerData(condition);
    }

    @ApiOperation("查询角标未分配的所有饮品")
    @PostMapping("/allMenus")
    public Response queryMenus(@RequestBody @Validated({Second.class}) MenuMarker condition) {
        return menuMarkerService.queryMenus(condition);
    }
}
