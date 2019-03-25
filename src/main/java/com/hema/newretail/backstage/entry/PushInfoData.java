package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 推送信息实体类
 */
@Data
public class PushInfoData implements Serializable {

    private String id;//消息Id
    private String title;//标题
    private String content;//内容
    private String operator;//发送者
    private String isWeiXin;//是否发送服务通知
    private List<String> userIsRead;//是否已读 如果用户已读，就将该用户添加到集合中
    private Date pushTime;
    private List<String> userIsDel;
    private List<String> users;//接收用户
    private String province="";
    private String city="";
    private String district="";


}
