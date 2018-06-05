package com.commerceerp.system.entity;

import com.commerceerp.system.util.DateUtil;
import com.commerceerp.system.util.SessionUtil;
import com.commerceerp.system.util.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/16.
 */
@Data
public class Entity implements Serializable{

    private String id;
    private String delFlag;
    private String addTime;
    private String addUserId;
    private String updTime;
    private String updUserId;
    private Integer page;
    private Integer limit;

    public void insert(){
        this.id = StringUtils.getUUID();
        this.delFlag = "0";
        this.addTime = DateUtil.getSystemTime("yyyy-MM-dd HH:mm:ss");
        this.addUserId = (String) SessionUtil.getSessionAttribute(Constant.LOGIN_USER_ID);
    }

    public void update(){
        this.updTime = DateUtil.getSystemTime("yyyy-MM-dd HH:mm:ss");
        this.updUserId = (String) SessionUtil.getSessionAttribute(Constant.LOGIN_USER_ID);
    }
}
