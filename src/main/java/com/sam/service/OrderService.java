package com.sam.service;

import com.sam.dto.CreateOrderRequest;
import com.sam.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
