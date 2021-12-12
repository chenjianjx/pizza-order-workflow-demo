package com.chenjianjx.order.workflow.demo.orderservice.biz.workflow;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

public interface OrderFlowService {

    void acceptOrder(UUID orderId, ObjectNode createOrderRequest);
}
