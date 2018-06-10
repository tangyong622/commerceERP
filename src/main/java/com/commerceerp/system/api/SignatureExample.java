package com.commerceerp.system.api;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class SignatureExample {

    private static final String CHARACTER_ENCODING = "UTF-8";
    final static String ALGORITHM = "HmacSHA256";

    private String endpoint = "webservices.amazon.com"; // must be lowercase
    private String awsAccessKeyId = "AKIAJ3NO6FXDVYTK3PZA";
    private String awsSecretKey = "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";


    public static void main(String[] args) throws Exception {
        // 把这个秘密钥匙换成你的
        String secretKey = "Rx3ifG2M922vrBaveo9cK5pf8/TI30dMnM+0Smpb";

        // 为市场使用端点
        String serviceUrl = "https://mws.amazonservices.com/";

        // 创建需要的参数集并存储在map中
        HashMap<String, String> parameters = new HashMap<String,String>();

        // 添加所需参数。根据需要改变这些。
        parameters.put("AWSAccessKeyId", urlEncode("AKIAJ3NO6FXDVYTK3PZA"));
        parameters.put("Action", urlEncode("listOrders"));
        parameters.put("MWSAuthToken", urlEncode("Your MWS Auth Token"));
        parameters.put("SellerId", urlEncode("Your Seller Id"));
        parameters.put("SignatureMethod", urlEncode(ALGORITHM));
        parameters.put("SignatureVersion", urlEncode("2"));
        parameters.put("SubmittedFromDate",
                urlEncode("2013-05-01T12:00:00Z"));
        parameters.put("Timestamp", urlEncode("2013-05-02T16:00:00Z"));
        parameters.put("Version", urlEncode("2009-01-01"));

        // 将参数以最终格式显示
        // (没有签名参数)
        String formattedParameters = calculateStringToSignV2(parameters,serviceUrl);

        String signature = sign(formattedParameters, secretKey);

        // 向参数添加签名并显示最终结果
        parameters.put("Signature", urlEncode(signature));
        System.out.println(calculateStringToSignV2(parameters, serviceUrl));
    }

    /* 如果签名版本为2，则要根据以下字符串签名：
    *
    *    1. HTTP请求方法，随后是ASCII换行符（%0A）
    *    2. 以小写主机形式的HTTP主机头，接着是ASCII换行符。
    *    3. URI的URL编码的HTTP绝对路径组件（最多但不包括查询字符串参数）；如果为空，则使用前向“/”。这个参数后面跟着一个ASCII换行符。
    *    4. 将所有查询字符串组件（名称和值）连接为UTF-8字符，URL编码为RFC 3986（十六进制字符必须大写），使用字典字节排序排序。
    *    参数名称由“=”字符（ASCII字符61）与它们的值分离，即使该值为空。参数和值对被“&”字符分隔开（ASCII代码38）。
    *
    */
    private static String calculateStringToSignV2(
            Map<String, String> parameters, String serviceUrl)
            throws SignatureException, URISyntaxException {
        // 按存储顺序按字母排序参数
        // 树形结构
        Map<String, String> sorted = new TreeMap<String, String>();
        sorted.putAll(parameters);

        // 设置端点值
        URI endpoint = new URI(serviceUrl.toLowerCase());

        // 创建扁平（字符串）表示
        StringBuilder data = new StringBuilder();
        data.append("POST\n");
        data.append(endpoint.getHost());
        data.append("\n/");
        data.append("\n");

        Iterator<Map.Entry<String, String>> pairs =
                sorted.entrySet().iterator();
        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = pairs.next();
            if (pair.getValue() != null) {
                data.append( pair.getKey() + "=" + pair.getValue());
            }
            else {
                data.append( pair.getKey() + "=");
            }

            // 参数中间以&链接
            if (pairs.hasNext()) {
                data.append( "&");
            }
        }

        return data.toString();
    }

    /*
     * 用给定的密钥签名文本并转换为Base64
     */
    private static String sign(String data, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            IllegalStateException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(secretKey.getBytes(CHARACTER_ENCODING),
                ALGORITHM));
        byte[] signature = mac.doFinal(data.getBytes(CHARACTER_ENCODING));
        String signatureBase64 = new String(Base64.encodeBase64(signature),
                CHARACTER_ENCODING);
        return new String(signatureBase64);
    }

    private static String urlEncode(String rawValue) {
        String value = (rawValue == null) ? "" : rawValue;
        String encoded = null;

        try {
            encoded = URLEncoder.encode(value, CHARACTER_ENCODING)
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E","~");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unknown encoding: " + CHARACTER_ENCODING);
            e.printStackTrace();
        }

        return encoded;
    }
}
