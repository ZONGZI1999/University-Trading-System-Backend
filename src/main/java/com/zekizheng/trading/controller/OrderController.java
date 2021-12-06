package com.zekizheng.trading.controller;

import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.ItemDetails;
import com.zekizheng.trading.entity.OrderDetails;
import com.zekizheng.trading.entity.UserBalance;
import com.zekizheng.trading.service.BalanceService;
import com.zekizheng.trading.service.ItemService;
import com.zekizheng.trading.service.OrderService;
import com.zekizheng.trading.status.ItemDetailsStatus;
import com.zekizheng.trading.status.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zongzi
 **/
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/createOrder")
    public HttpBaseResponse<OrderDetails> createOrder(@RequestBody OrderDetails orderDetails){
        HttpBaseResponse<OrderDetails> resp = new HttpBaseResponse<>();
        String buyerId = "SWE1809388";
        Integer itemId = orderDetails.getItemId();
        ItemDetails itemDetails = itemService.queryOneItem(itemId);
        if (itemDetails == null) {
            String errorMsg = "item id not correct!";
            log.error(errorMsg);
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription(errorMsg);
            return resp;
        }

        if(buyerId.equals(itemDetails.getSellerId())) {
            String errMsg = "Can’t buy self-published item";
            log.error(errMsg);
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription(errMsg);
            return resp;
        }

        if (itemDetails.getItemStatus() != ItemDetailsStatus.ON_SELL) {
            String errorMsg = "item status not correct";
            log.error(errorMsg);
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription(errorMsg);
            return resp;
        }
        // create order
        String sellerId = itemDetails.getSellerId();
        orderDetails.setSellerId(sellerId);
        orderDetails.setBuyerId(buyerId);
        log.info("create order");
        int row = orderService.createOrder(orderDetails);
        if (row != 1) {
            String errMsg = "fail to create order";
            log.error(errMsg);
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            resp.setDescription(errMsg);
            return resp;
        }
        itemDetails.setItemStatus(ItemDetailsStatus.SOLD);
        //set item to sold status
        itemService.updateItem(itemDetails);
        log.info("create order successfully");
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(orderDetails);
        return resp;
    }

    @PostMapping("/payOrder")
    public HttpBaseResponse<OrderDetails> payOrder(@RequestBody OrderDetails orderDetails) {
        HttpBaseResponse<OrderDetails> resp = new HttpBaseResponse<>();
        resp.setMessage(ResponseCode.UNKNOWN_ERROR);
        String buyerId = "SWE1809388";
        if (orderDetails == null ||
                orderDetails.getOrderId()== null ||
                orderDetails.getOrderId().isEmpty()) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Order Details is null");
            return resp;
        }
        //从db中获取最新的订单数据
        orderDetails = orderService.queryDetails(orderDetails.getOrderId());

        if (orderDetails == null) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("order not found");
            return resp;
        }

        if (!buyerId.equals(orderDetails.getBuyerId())) {
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("order is not belonging to you");
            return resp;
        }

        if(!orderDetails.getOrderStatus().equals(OrderStatus.CREATED)) {
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("this order status is not correct");
            return resp;
        }

        ItemDetails itemDetails = itemService.queryOneItem(orderDetails.getItemId());
        int itemPrice = itemDetails.getItemPrice();

        UserBalance userBalance = balanceService.getUserBalance(buyerId);
        int beforeUserPayBalance = userBalance.getBalance();

        if (beforeUserPayBalance - itemPrice < 0) {
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("Balance is not enough!");
            return resp;
        }

        int row = orderService.payOrder(orderDetails);

        if(row == 1) {
            resp.setMessage(ResponseCode.SUCCESS);
            resp.setData(orderDetails);
            return resp;
        }

        return resp;
    }

    @PostMapping({"/setDelivery", "/onConfirm", "/giveEvaluation"})
    public HttpBaseResponse<OrderDetails> setDeliveryInfo(@RequestBody OrderDetails orderDetails) {
        HttpBaseResponse<OrderDetails> resp = new HttpBaseResponse<>();
        resp.setMessage(ResponseCode.UNKNOWN_ERROR);

        String userId = "SWE1809387";
        if(orderDetails == null ||
                orderDetails.getOrderId() == null ||
                orderDetails.getOrderId().isEmpty()) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Order id is empty!");
            return resp;
        }

        Date deliveryTime = new Date();
        String deliveryCompany = orderDetails.getDeliveryCompany();
        String trackingNo = orderDetails.getTrackingNo();
        String sellerEvaluation = orderDetails.getSellerEvaluation();
        String buyerEvaluation = orderDetails.getBuyerEvaluation();

        orderDetails = orderService.queryDetails(orderDetails.getOrderId());

        if (orderDetails == null) {
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("Order details is not found!");
            return resp;
        }
        int row;
        switch (orderDetails.getOrderStatus()) {
            case PAID:
                if(!orderDetails.getSellerId().equals(userId)) {
                    resp.setMessage(ResponseCode.UNKNOWN_ERROR);
                    resp.setDescription("You cannot to set delivery info.");
                    return resp;
                }
                if(deliveryCompany == null || deliveryCompany.isEmpty()) {
                    resp.setMessage(ResponseCode.PARAM_ERROR);
                    resp.setDescription("Delivery Company is empty!");
                    return resp;
                }

                if(trackingNo == null || trackingNo.isEmpty()) {
                    resp.setMessage(ResponseCode.PARAM_ERROR);
                    resp.setDescription("Tracking No is empty!");
                    return resp;
                }
                orderDetails.setDeliveryTime(deliveryTime);
                orderDetails.setTrackingNo(trackingNo);
                orderDetails.setDeliveryCompany(deliveryCompany);
                orderDetails.setOrderStatus(OrderStatus.ON_DELIVERY);

                row = orderService.updateOrder(orderDetails);

                if(row != 1) {
                    resp.setMessage(ResponseCode.DATABASE_ERROR);
                } else {
                    resp.setMessage(ResponseCode.SUCCESS);
                    resp.setData(orderDetails);
                }
                return resp;
            case ON_DELIVERY:
                if(!orderDetails.getBuyerId().equals(userId)) {
                    resp.setMessage(ResponseCode.UNKNOWN_ERROR);
                    resp.setDescription("You cannot to confirm this order!");
                    return resp;
                }
                orderDetails.setOrderStatus(OrderStatus.ON_RECEIVED);

                row = orderService.updateOrder(orderDetails);

                if(row != 1) {
                    resp.setMessage(ResponseCode.DATABASE_ERROR);
                } else {
                    resp.setMessage(ResponseCode.SUCCESS);
                    resp.setData(orderDetails);
                }
                return resp;
            case ON_RECEIVED:
                //try to set buyer evaluation
                if(orderDetails.getBuyerId().equals(userId)) {
                    if (orderDetails.getBuyerEvaluation() != null) {
                        resp.setMessage(ResponseCode.PARAM_ERROR);
                        resp.setDescription("You has given evaluation");
                        return resp;
                    } else {
                        if(buyerEvaluation == null){
                            resp.setMessage(ResponseCode.PARAM_ERROR);
                            resp.setDescription("your evaluation is empty");
                            return resp;
                        }
                        orderDetails.setBuyerEvaluation(buyerEvaluation);
                    }
                } else if(orderDetails.getSellerId().equals(userId)) {
                    if (orderDetails.getSellerEvaluation() != null) {
                        resp.setMessage(ResponseCode.PARAM_ERROR);
                        resp.setDescription("You has given evaluation");
                        return resp;
                    } else {
                        if(sellerEvaluation == null) {
                            resp.setMessage(ResponseCode.PARAM_ERROR);
                            resp.setDescription("your evaluation is empty");
                            return resp;
                        }
                        orderDetails.setSellerEvaluation(sellerEvaluation);
                    }
                }
                row = orderService.updateOrder(orderDetails);
                if (row != 1) {
                    resp.setMessage(ResponseCode.DATABASE_ERROR);
                    return resp;
                }
                if(orderDetails.getBuyerEvaluation() != null && orderDetails.getSellerEvaluation() != null) {
                    orderDetails.setOrderStatus(OrderStatus.FINISH);
                }
                row = orderService.updateOrder(orderDetails);
                if (row != 1) {
                    resp.setMessage(ResponseCode.DATABASE_ERROR);
                    return resp;
                }
                resp.setMessage(ResponseCode.SUCCESS);
                return resp;
            default:
                resp.setMessage(ResponseCode.UNKNOWN_ERROR);
                resp.setDescription("In this status, you cannot operate it.");
                return resp;
        }
    }

    @GetMapping("/queryOrderList/{as}")
    public HttpBaseResponse<List<OrderDetails>> queryOrderList(@PathVariable String as,
                                                               @RequestParam String studentId) {
        //todo: 鉴权
        HttpBaseResponse<List<OrderDetails>> resp = new HttpBaseResponse<>();
        if (studentId == null) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("student id is empty");
            return resp;
        }
        if (as.equals("buyer")) {
            List<OrderDetails> res = orderService.queryAllOrderAsBuyer(studentId);
            resp.setMessage(ResponseCode.SUCCESS);
            resp.setData(res);
            return resp;
        } else if (as.equals("seller")) {
            List<OrderDetails> res = orderService.queryAllOrderAsSeller(studentId);
            resp.setMessage(ResponseCode.SUCCESS);
            resp.setData(res);
            return resp;
        } else if (as.equals("all")){
            List<OrderDetails> res = orderService.queryAllOrder(studentId);
            resp.setMessage(ResponseCode.SUCCESS);
            resp.setData(res);
            return resp;
        } else {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("param error");
            return resp;
        }
    }

    @GetMapping("/queryOrder")
    public HttpBaseResponse<OrderDetails> queryOrderDetails(@RequestParam String orderId) {
        HttpBaseResponse<OrderDetails> resp = new HttpBaseResponse<>();

        String userId = "SWE1809388";

        OrderDetails orderDetails = orderService.queryDetails(orderId);
        if(orderDetails == null) {
            log.error("order id is not exist");
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("order id is not exist");
            return resp;
        }
        if (!orderDetails.getBuyerId().equals(userId) && orderDetails.getSellerId().equals(userId)) {
            log.error("this order is not belong to you");
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("this order is not belong to you");
            return resp;
        }
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(orderDetails);

        return resp;
    }
}
