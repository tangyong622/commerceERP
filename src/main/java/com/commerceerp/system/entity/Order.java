package com.commerceerp.system.entity;

import lombok.Data;

/**
 * Created by Administrator on 2018/6/10/010.
 */
@Data
public class Order extends Entity{

    private String orderNo;//订单编号
    private String productId;//商品id
    private String productName;//商品名称
    private String orderTotal;//订单金额
    private String buyerId;//买家id
    private String name;//收件人
    private String country;//国家
    private String city;//地区
    private String shopId;//店铺id
    private String skuNo;//sku编号
    private String createTime;//下单时间
    private String payTime;//付款时间
    private String logistics;//物流方式
    private String orderStatus;//订单状态


}
