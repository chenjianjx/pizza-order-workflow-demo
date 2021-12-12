package com.chenjianjx.order.workflow.demo.orderservice.biz.workflow;

import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.ApproveOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderRequest;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

public interface OrderFlowService {

    void acceptOrder(UUID orderId, CreateOrderRequest createOrderRequest);

    void approve(ApproveOrderRequest approveOrderRequest);

    void pizzaPrepared(UUID orderId);

    ObjectNode getStatus(UUID orderId);
}
