package com.hema.newretail.backstage.controller;

import com.hema.newretail.backstage.common.queryparam.agent.TestControllerCondition;
import com.hema.newretail.backstage.common.utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-09-07 12:12
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @PostMapping(value = "/hello")
    @ResponseBody
    public Response hello(@RequestBody @Validated TestControllerCondition testControllerCondition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
                System.out.println("in hello....");
                return Response.success("hell0");
        }

    }
    @PostMapping(value = "/vue")
    @ResponseBody
    public Response vue(@RequestBody @Validated TestControllerCondition testControllerCondition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            List<Map> map = new ArrayList<>();
            Map map1 = new HashMap(5);
            map1.put("id",1);
            map1.put("name","曹仁");
            map1.put("job","虎豹骑统领、大将军、大司马");
            map1.put("card","渭南破马超，襄樊拒关羽，协徐晃共破沉邵");
            map.add(map1);
            Map map2 = new HashMap(5);
            map2.put("id",1);
            map2.put("name","曹真");
            map2.put("job","大将军");
            map2.put("card","曹操螟蛉之子");
            map.add(map2);
            Map map3 = new HashMap(5);
            map3.put("id",1);
            map3.put("name","曹睿");
            map3.put("job","魏明帝");
            map3.put("card","魏文帝曹丕甄妃之子");
            map.add(map3);
            System.out.println("in hello....");
            return Response.success(map);
        }

    }

}
