package com.chenjianjx.order.workflow.demo.temporalio.flow.impl;

import com.chenjianjx.order.workflow.demo.temporalio.flow.OrderActivity;
import com.chenjianjx.order.workflow.demo.temporalio.flow.OrderFlow;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.workflow.Workflow;

import java.util.UUID;


public class OrderFlowImpl implements OrderFlow {

    private OrderActivity orderActivity = Workflow.newActivityStub(OrderActivity.class);

    @Override
    public void acceptOrder(UUID orderId, ObjectNode createOrderRequest) {
        orderActivity.persistNewOrder(orderId, createOrderRequest);
    }
}
