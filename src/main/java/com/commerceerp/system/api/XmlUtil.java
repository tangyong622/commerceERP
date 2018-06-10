package com.commerceerp.system.api;//先加入dom4j.jar包
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.commerceerp.system.util.StringUtils;
import org.dom4j.*;

/**
 * @Title: TestDom4j.java
 * @Package
 * @Description: 解析xml字符串
 * @author 无处不在
 * @date 2012-11-20 下午05:14:05
 * @version V1.0
 */
public class XmlUtil {

    public void readStringXml(String xml) {
        Document doc = null;
        try {

            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            // 
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));
            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML

            Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

            Iterator iter = rootElt.elementIterator("head"); // 获取根节点下的子节点head

            // 遍历head节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值
                System.out.println("title:" + title);

                Iterator iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script

                // 遍历Header节点下的Response节点
                while (iters.hasNext()) {

                    Element itemEle = (Element) iters.next();

                    String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
                    String password = itemEle.elementTextTrim("password");

                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                }
            }
            Iterator iterss = rootElt.elementIterator("body"); ///获取根节点下的子节点body
            // 遍历body节点
            while (iterss.hasNext()) {

                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值
                System.out.println("result:" + result);

                Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form
                // 遍历Header节点下的Response节点
                while (itersElIterator.hasNext()) {

                    Element itemEle = (Element) itersElIterator.next();

                    String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
                    String subID = itemEle.elementTextTrim("subID");

                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map readStringXmlOut(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 拿到根节点的名称
            System.out.println("根节点：" + rootElt.getName());
            // 获取根节点下的子节点ListOrdersResult
            Iterator iter = rootElt.elementIterator("ListOrdersResult");
            while (iter.hasNext()) {
                Element itemEle = (Element) iter.next();
                Iterator Orders = itemEle.elementIterator("Orders");
                while (Orders.hasNext()) {
                    Element order = (Element) Orders.next();
                    Iterator Order = order.elementIterator("Order");
                    while (Order.hasNext()) {
                        Element msg = (Element) Order.next();
                        for (Iterator<?> iterator2 = msg.elementIterator(); iterator2.hasNext();) {
                            Element element2 = (Element) iterator2.next();
                            System.out.println(element2.getName()+":"+element2.getTextTrim());
                        }
                    }

                }
            }

            // 获取根节点下的子节点ListOrdersResult
            //Iterator iter = rootElt.elementIterator("ListOrdersResult");
            // 遍历head节点
//            while (iter.hasNext()) {
//                Element recordEle = (Element) iter.next();
//                // 拿到ListOrdersResult节点下的子节点NextToken值
//                String NextToken = recordEle.elementTextTrim("NextToken");
//                System.out.println("NextToken:" + NextToken);
//                map.put("NextToken", NextToken);
//                String LastUpdatedBefore = recordEle.elementTextTrim("LastUpdatedBefore");
//                System.out.println("LastUpdatedBefore:" + LastUpdatedBefore);
//                map.put("LastUpdatedBefore", LastUpdatedBefore);
//                // 获取子节点ListOrdersResult下的子节点Orders
//                Iterator iters = recordEle.elementIterator("Orders");
//                // 遍历Orders节点下的Response节点
//                while (iters.hasNext()) {
//                    Element itemEle = (Element) iters.next();
//                    // 拿到head下的子节点script下的字节点username的值
//                    String username = itemEle.elementTextTrim("Orders");
//                    String password = itemEle.elementTextTrim("ShipmentServiceLevelCategory");
//                    System.out.println("username:" + username);
//                    System.out.println("password:" + password);
//                    map.put("username", username);
//                    map.put("password", password);
//                }
//            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {

        // 下面是需要解析的xml字符串例子
        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<ListOrdersResponse xmlns=\"https://mws.amazonservices.com/Orders/2013-09-01\">" +
                    "<ListOrdersResult>" +
                        "<NextToken>2YgYW55IGNhcm5hbCBwbGVhc3VyZS4=</NextToken>" +
                        "<LastUpdatedBefore>2013-09-25T18%3A10%3A21.687Z</LastUpdatedBefore>" +
                        "<Orders>" +
                            "<Order>" +
                                "<ShipmentServiceLevelCategory>Standard</ShipmentServiceLevelCategory>" +
                                "<ShipServiceLevel>Std JP Kanto8</ShipServiceLevel>" +
                                "<EarliestShipDate>2013-08-20T19:51:16Z</EarliestShipDate>" +
                                "<LatestShipDate>2013-08-25T19:49:35Z</LatestShipDate>" +
                                "<MarketplaceId>A1VC38T7YXB528</MarketplaceId>" +
                                "<SalesChannel>Amazon.com</SalesChannel>" +
                                "<OrderType>Preorder</OrderType>" +
                                "<BuyerEmail>5vlhEXAMPLEh9h5@marketplace.amazon.com</BuyerEmail>" +
                                "<FulfillmentChannel>MFN</FulfillmentChannel>" +
                                "<OrderStatus>Pending</OrderStatus>" +
                                "<BuyerName>John Jones</BuyerName>" +
                                "<LastUpdateDate>2013-08-20T19:49:35Z</LastUpdateDate>" +
                                "<PurchaseDate>2013-08-20T19:49:35Z</PurchaseDate>" +
                                "<NumberOfItemsShipped>0</NumberOfItemsShipped>" +
                                "<NumberOfItemsUnshipped>0</NumberOfItemsUnshipped>" +
                                "<AmazonOrderId>902-3159896-1390916</AmazonOrderId>" +
                                "<PaymentMethod>Other</PaymentMethod>" +
                            "</Order>" +
                            "<Order>" +
                            "<ShipmentServiceLevelCategory>Standard</ShipmentServiceLevelCategory>" +
                            "<ShipServiceLevel>Std JP Kanto8</ShipServiceLevel>" +
                            "<EarliestShipDate>2013-08-20T19:51:16Z</EarliestShipDate>" +
                            "<LatestShipDate>2013-08-25T19:49:35Z</LatestShipDate>" +
                            "<MarketplaceId>A1VC38T7YXB528</MarketplaceId>" +
                            "<SalesChannel>Amazon.com</SalesChannel>" +
                            "<OrderType>Preorder</OrderType>" +
                            "<BuyerEmail>5vlhEXAMPLEh9h5@marketplace.amazon.com</BuyerEmail>" +
                            "<FulfillmentChannel>MFN</FulfillmentChannel>" +
                            "<OrderStatus>Pending</OrderStatus>" +
                            "<BuyerName>John Jones</BuyerName>" +
                            "<LastUpdateDate>2013-08-20T19:49:35Z</LastUpdateDate>" +
                            "<PurchaseDate>2013-08-20T19:49:35Z</PurchaseDate>" +
                            "<NumberOfItemsShipped>0</NumberOfItemsShipped>" +
                            "<NumberOfItemsUnshipped>0</NumberOfItemsUnshipped>" +
                            "<AmazonOrderId>902-3159896-1390916</AmazonOrderId>" +
                            "<PaymentMethod>Other</PaymentMethod>" +
                            "</Order>" +
                        "</Orders>" +
                    "</ListOrdersResult>" +
                    "<ResponseMetadata>" +
                        "<RequestId>88faca76-b600-46d2-b53c-0c8c4533e43a</RequestId>" +
                    "</ResponseMetadata>" +
                "</ListOrdersResponse>";

        /*
         * Test2 test = new Test2(); test.readStringXml(xmlString);
         */
        Map map = readStringXmlOut(xmlString);
        Iterator iters = map.keySet().iterator();
        while (iters.hasNext()) {
            String key = iters.next().toString(); // 拿到键
            String val = map.get(key).toString(); // 拿到值
            System.out.println(key + "=" + val);
        }
    }

}