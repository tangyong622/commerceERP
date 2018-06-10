package com.commerceerp.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * Created by Administrator on 2018/3/16.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    //自动生成id
    public static String getUUID(){

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 接口调用 GET
     */
    public static String httpURLConectionGET(String getUrl) {
        try {
            URL url = new URL(getUrl);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
            return null;
        }

    }

    /**
     * 发送HttpPost请求
     *
     * @param postUrl 服务地址
     * @param param json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    public static void httpURLConnectionPOST (String postUrl,String param) {
        try {
            URL url = new URL(postUrl);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(param);
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringBuffer content = new StringBuffer();
            while ((line = in.readLine()) != null) {
                // line 为返回值，这就可以判断是否成功
                content.append(line);
            }
            in.close();
            System.out.println(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }

    /**
     * 删除
     * @param delUrl
     * @return
     */
    public static void httpURLConnectionDelete(String delUrl){
        try{
            URL url = new URL(delUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("name", "robben");
            connection.setRequestProperty("content-type", "text/html");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
            out.flush();
            out.close();
            // 获取返回的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringBuffer content = new StringBuffer();
            while ((line = in.readLine()) != null) {
                // line 为返回值，这就可以判断是否成功
                content.append(line);
            }
            in.close();
            System.out.println(content.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("error");
    }



    public static void main(String[] args) {
        httpURLConectionGET("https://mws.amazonservices.com/FulfillmentInventory/2010-10-01" +
                "?Action=ListInventorySupply" +
                "&Version=2011-03-01" +
                "&AWSAccessKeyId=AKIAJGUEXAMPLEE2NVUA" +
                "&MWSAuthToken=amzn.mws.4ea38b7b-f563-7709-4bae-87aeaEXAMPLE" +
                "&SignatureVersion=2" +
                "&SignatureMethod=HmacSHA256" +
                "&Signature=ZRA9DR5rveSuz%2F1D18AHvoipg2BAev8yblPQ1BbEbfU%3D" +
                "&Timestamp=2010-10-01T02:40:36Z" +
                "&SellerId=A2NKEXAMPLEF53" +
                "&SellerSkus.member.1=SampleSKU1" +
                "&SellerSkus.member.2=SampleSKU2" +
                "&ResponseGroup=Basic");

    }
}
