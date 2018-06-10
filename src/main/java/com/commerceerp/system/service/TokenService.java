package com.commerceerp.system.service;

import com.commerceerp.system.mapper.TokenMapper;
import com.commerceerp.system.util.JsonResult;
import com.commerceerp.system.util.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/5/13/013.
 */
@Service
public class TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    public JsonResult saveToken(String code) {

        tokenMapper.saveToken(code, StringUtils.getUUID(),"code");
        getToken(code);
        System.out.println("接收到code："+code);
        return new JsonResult(0,"接收到code");
    }

    public JsonResult getToken(String authorization_code){

        StringBuffer url = new StringBuffer("https://sandbox.merchant.wish.com/api/v2/oauth/access_token?");
        url.append("client_id=5b1a715e8c6f492ba58d7429");
        url.append("&client_secret=2b6b0cc980124ae1af5edb9a01dee243");
        url.append("&code="+authorization_code);
        url.append("&redirect_uri=https://www.mind-erp.com/wish/authorization_code");
        url.append("&grant_type=authorization_code");
        String result = StringUtils.httpURLConectionGET(url.toString());
        if(StringUtils.isNotEmpty(result)) {
            JSONObject json = JSONObject.fromObject(result);
            if(json != null) {
                int code = json.getInt("code");
                if(code == 0){
                    JSONObject data = json.optJSONObject("data");
                    String access_token = data.getString("access_token");
                    tokenMapper.saveToken(access_token, StringUtils.getUUID(),"token");
                }else{
                    return new JsonResult(400,json.getString("message"));
                }
            }
        }
        return new JsonResult(400,"请求API异常");
    }
}
