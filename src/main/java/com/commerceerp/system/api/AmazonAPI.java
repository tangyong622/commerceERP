package com.commerceerp.system.api;

import com.commerceerp.system.util.StringUtils;
import net.sf.json.JSONObject;

/**
 * 亚马逊
 * Created by Administrator on 2018/6/6.
 */
public class AmazonAPI {

    private final String Merchant_ID = "";
    private final String AWS_Access_Key_ID="";
    private final String Secret_Key="";

    /**
     * 订单列表
     * @param CreatedAfter
     * 指定某一格式为 ISO 8601 的日期，用以选择在该日期之后（或当天）创建的订单。
     * @param CreatedBefore
     * 指定某一格式为 ISO 8601 的日期，用以选择在该日期之前（或当天）创建的订单
     * @param LastUpdatedAfter
     * 指定某一格式为 ISO 8601 的日期，用以选择最后更新日期为该日期之后（或当天）的订单。更新即为对订单状态进行更改，包括新订单的创建。包括亚马逊和卖家所进行的更新。
     * @param LastUpdatedBefore
     * 指定某一格式为 ISO 8601 的日期，用以选择最后更新日期为该日期之前（或当天）的订单。更新即为对订单状态进行更改，包括新订单的创建。包括亚马逊和卖家所进行的更新。
     * @param OrderStatus
     * 用来选择当前状态与您所指定的某个状态值相符的订单。
     * PendingAvailability
     * 只有预订订单才有此状态。订单已生成，但是付款未授权且商品的发售日期是将来的某一天。订单尚不能进行发货。请注意：仅在日本 (JP)，Preorder 才可能是一个 OrderType 值。
     * Pending
     * 订单已生成，但是付款未授权。订单尚不能进行发货。请注意：对于 OrderType = Standard 的订单，初始的订单状态是 Pending。对于 OrderType = Preorder 的订单（仅适用于 JP），初始的订单状态是 PendingAvailability，当进入付款授权流程时，订单状态将变为 Pending。
     * Unshipped
     * 付款已经过授权，订单已准备好进行发货，但订单中商品尚未发运。
     * PartiallyShipped
     * 订单中的一个或多个（但并非全部）商品已经发货。
     * Shipped
     * 订单中的所有商品均已发货。
     * InvoiceUnconfirmed
     * 订单中的所有商品均已发货。但是卖家还没有向亚马逊确认已经向买家寄出发票。请注意：此参数仅适用于中国地区。
     * Canceled
     * 订单已取消。
     * Unfulfillable
     * 订单无法进行配送。该状态仅适用于通过亚马逊零售网站之外的渠道下达但由亚马逊进行配送的订单。
     * @param MarketplaceId
     * MarketplaceId 值的列表。用来选择您所指定商城中的订单。
     * @param FulfillmentChannel
     * 指明订单配送方式的结构化列表。
     * @param PaymentMethod
     * PaymentMethod 值的列表。用来选择您指定的订单付款方式。COD 货到现金付款,CVS 便利店付款, Other,  COD 或 CVS 之外的任意付款方式
     * @param BuyerEmail
     * 买家的电子邮件地址。用来选择包含指定电子邮件地址的订单。
     * @param SellerOrderId
     * 卖家所指定的订单编码。不是亚马逊订单编号。用来选择与卖家所指定订单编码相匹配的订单。
     * @param MaxResultsPerPage
     * 该数字用来指明每页可返回的最多订单数。
     * @param TFMShipmentStatus
     * TFMShipmentStatus 值的列表。用于选择使用亚马逊配送服务 (TFM) 且当前配送状态与您指定的某个状态值相符的订单。如果指定 TFMShipmentStatus，则仅返回 TFM 订单
     * PendingPickUp 亚马逊尚未从卖家处取件。 LabelCanceled 卖家取消了取件。PickedUp 亚马逊已从卖家处取件。 AtDestinationFC 包裹已经抵达亚马逊运营中心。 Delivered 包裹已经配送给买家。RejectedByBuyer 包裹被买家拒收。 Undeliverable 包裹无法配送。 ReturnedToSeller 包裹未配送给买家，已经退还给卖家。 Lost 包裹被承运人丢失。
     */
    public void listOrders(String CreatedAfter,String CreatedBefore,String LastUpdatedAfter,String LastUpdatedBefore,String OrderStatus,String MarketplaceId,
                           String FulfillmentChannel,String PaymentMethod,String BuyerEmail,String SellerOrderId,String MaxResultsPerPage,String TFMShipmentStatus
                           ){
        JSONObject param = new JSONObject();
        param.element("AWSAccessKeyId", AWS_Access_Key_ID);
        param.element("MWSAuthToken", "");
        param.element("AWSAccessKeyId", "");
        StringUtils.httpURLConnectionPOST("https://mws.amazonservices.jp/Orders/2013-09-01",param.toString());
    }
}
