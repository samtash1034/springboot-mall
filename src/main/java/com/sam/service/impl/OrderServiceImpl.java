package com.sam.service.impl;

import com.sam.dao.OrderDao;
import com.sam.dao.ProductDao;
import com.sam.dao.UserDao;
import com.sam.dto.BuyItem;
import com.sam.dto.CreateOrderRequest;
import com.sam.model.Order;
import com.sam.model.OrderItem;
import com.sam.model.Product;
import com.sam.model.User;
import com.sam.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Transactional//關聯的表超過一張以上(同時發生或不發生)
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);
        if(user == null){
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            if(product == null){
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            if(buyItem.getQuantity() > product.getStock()){
                log.warn("商品 {} 數量不足，無法購買。剩餘庫存 {}，欲購買數量 {}", buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //更新庫存的商品數量
            productDao.updateStock(buyItem.getProductId(), product.getStock() - buyItem.getQuantity());
        }

        //商業邏輯
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(totalAmount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItem(orderId, orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }
}
