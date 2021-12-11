package com.chenjianjx.order.workflow.demo.orderservice.biz;

import com.chenjianjx.order.workflow.demo.orderservice.biz.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OrderRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public void insert(Order order) {
        mongoTemplate.insert(order);
    }

    public Order getOrderById(UUID orderId) {
        return mongoTemplate.findById(orderId, Order.class);
    }
}
