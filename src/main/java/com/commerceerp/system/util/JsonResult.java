package com.commerceerp.system.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -2565603013486909913L;


    private int code;//0:代表成功;1或其他�?�代表处理失�?
    private Object data;//接收返回的数�?
    private String msg;//定义提示 信息
    private int count;//返回记录总条数

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;


    public JsonResult() {
        super();
    }

    public JsonResult(int code, Throwable e) {
        this.code = code;
        msg = e.getMessage();
        data = null;
    }

    public JsonResult(int code, Object data, String msg, int count) {
        super();
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        code = ERROR;
        msg = e.getMessage();
        data = null;
    }

    public JsonResult(int count, Object data) {
        code = SUCCESS;
        msg = "";
        this.count = count;
        this.data = data;


    }

    public JsonResult(Object data) {
        code = SUCCESS;
        msg = "";
        this.count = 1;
        this.data = data;
    }


    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        count = 0;
        data = null;
    }
}