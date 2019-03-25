package com.hema.newretail.backstage.controller;

import com.hema.newretail.backstage.common.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-23 17:45
 */
@RestController
@RequestMapping("/sys/ueditor")
@CrossOrigin
public class UeditorController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UeditorController.class);

    @RequestMapping(value = "/exec")
    public String exec(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        logger.info("rootPath=={}", rootPath);
        String str = new ActionEnter(request, rootPath).exec();
        logger.info("exec=={}", str);
        return str;
    }
}
