package com.chenjianjx.order.workflow.demo.temporalio.flow.impl;

import com.chenjianjx.order.workflow.demo.temporalio.flow.OrderActivities;
import com.chenjianjx.order.workflow.demo.temporalio.flow.OrderFlow;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.UUID;


public class OrderFlowImpl implements OrderFlow {

    private OrderActivities orderActivities = Workflow.newActivityStub(OrderActivities.class,
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build()
    );

    @Override
    public void acceptOrder(UUID orderId, ObjectNode createOrderRequest) {
        orderActivities.persistNewOrder(orderId, createOrderRequest);
    }
}
