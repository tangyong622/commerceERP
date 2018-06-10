package com.commerceerp.system.api;

import com.commerceerp.system.entity.Order;
import com.commerceerp.system.service.TokenService;
import com.commerceerp.system.util.JsonResult;
import com.commerceerp.system.util.StringUtils;
import com.google.common.collect.Lists;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2018/6/10/010.
 */
@Controller
@RequestMapping("sys")
public class WishApi {

    private TokenService tokenService;

    //未完成的订单
    public static JsonResult getFulfill(String access_token,String start,String limit){
        StringBuffer url = new StringBuffer("https://sandbox.merchant.wish.com/api/v2/order/get-fulfill?");
        url.append("access_token="+access_token);
        url.append("&start="+start);
        url.append("&limit="+limit);
        String result = StringUtils.httpURLConectionGET(url.toString());
        if(StringUtils.isNotEmpty(result)){
            JSONObject json = JSONObject.fromObject(result);
            if(json != null){
                int code = json.getInt("code");
                if(code == 0){
                    List<Order> orders = Lists.newArrayList();
                    JSONArray data=json.getJSONArray("data");
                    //遍历数组获取元素----对象
                    for(int i=0;i<data.size();i++){
                        Order o = new Order();
                        //解析第三层----对象
                        JSONObject child = data.getJSONObject(i);
                        JSONObject order = child.optJSONObject("Order");//
                        String updTime = order.getString("last_updated");//最后更新时间
                        o.setUpdTime(updTime);
                        String expectedShipDate = order.getString("expected_ship_date");//如果订单在该日期之前没有达到，它将自动退款
                        String quantity = order.getString("quantity");//购买数量
                        String color = order.getString("color");//颜色
                        String price = order.getString("price");//单价
                        String variantId = order.getString("variant_id");//订购产品变异的唯一标识符
                        String isWishExpress = order.getString("is_wish_express");//如果订单是Wish Express订单，则为真
                        String cost = order.getString("cost");//Wish为您支付产品的金额（每单位）
                        String shippingCost = order.getString("shipping_cost");//Wish支付您运送产品的金额（每个单位）
                        String hoursToFulfill = order.getString("hours_to_fulfill");//完成此订单所剩的小时数。如果订单未能及时履行，将自动退还。
                        String requires_delivery_confirmation = order.getString("requires_delivery_confirmation");//如果为True，则必须使用合格的承运人运输订单，并使用确认最后一英里追踪的交货的服务。请点击此处查看
                        String productImageUrl = order.getString("product_image_url");//商品图片
                        String size = order.getString("size");//订购产品的尺寸
                        String sku = order.getString("sku");//sku
                        o.setSkuNo(sku);
                        String tracking_confirmed = order.getString("tracking_confirmed");//跟踪是否被确认
                        String orderTotal = order.getString("order_total");//订单的总价值
                        o.setOrderTotal(orderTotal);
                        String productId = order.getString("product_id");//产品id
                        o.setProductId(productId);
                        String shipping = order.getString("shipping");//用户为产品运费支付的金额（每单位）
                        String orderId = order.getString("order_id");//订单id
                        o.setOrderNo(orderId);
                        String orderStatus = order.getString("state");//订单状态 APPROVED发货 SHIPPED已发货 REFUNDED退款 REQUIRE_REVIEW：审核中，请勿发货
                        o.setOrderStatus(orderStatus);
                        String releasedToMerchantTime = order.getString("released_to_merchant_time");//订单发布给商家的日期和时间，以及计算完成日期的开始时间
                        String isCombinedOrder = order.getString("is_combined_order");//
                        String orderTime = order.getString("order_time");//订单发出的日期和时间
                        o.setCreateTime(orderTime);
                        String daysToFulfill = order.getString("days_to_fulfill");//完成此订单所剩的天数。如果订单未能及时履行，将自动退还。
                        String productName = order.getString("product_name");//产品名称
                        String transactionId = order.getString("transaction_id");//如果用户同时进行多次购买，订单可以归入唯一标识符下
                        String buyerId = order.getString("buyer_id");//买家
                        o.setBuyerId(buyerId);

                        JSONObject shippingDetail = order.optJSONObject("ShippingDetail");//收件人信息
                        String phoneNumber = shippingDetail.getString("phone_number");//收件人号码
                        String city = shippingDetail.getString("city");//收件城市
                        o.setCity(city);
                        String state = shippingDetail.getString("state");//收件地区
                        String name = shippingDetail.getString("name");//收件人
                        o.setName(name);
                        String country = shippingDetail.getString("country");//收件国家
                        o.setCountry(country);
                        String zipcode = shippingDetail.getString("zipcode");//邮编
                        String streetAddress = shippingDetail.getString("street_address1");//收件地址
                        System.out.println(o.toString());
                        orders.add(o);
                    }
                }else{
                    return new JsonResult(400,json.getString("message"));
                }
            }
        }
        return new JsonResult(400,"请求API异常");
    }

    public static void main(String[] args) {
        getFulfill("73bf4f2f20d24320a579bf8509782dcc","0","10");
    }


}
