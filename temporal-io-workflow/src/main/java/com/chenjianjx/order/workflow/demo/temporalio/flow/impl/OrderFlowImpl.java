package com.chenjianjx.order.workflow.demo.temporalio.flow.impl;

import com.chenjianjx.order.workflow.demo.temporalio.flow.CompleteResult;
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

    private Boolean approved;
    private ObjectNode approveOrderRequest;
    private final FlowStatus flowStatus = new FlowStatus();
    private Object pizzaPrepared;


    @Override
    public void acceptOrder(UUID orderId, ObjectNode createOrderRequest) {
        orderActivities.persistNewOrder(orderId, createOrderRequest);
        this.flowStatus.setLastActivity("persistNewOrder");

        Workflow.await(() -> this.approved != null);
        this.flowStatus.setLastActivity(this.approved ? "Approve" : "Deny");

        if (!approved) {
            orderActivities.sendRejectionEmail(orderId, approveOrderRequest);
            this.flowStatus.setLastActivity("sendRejectionEmail");
            this.flowStatus.setCompleteResult(CompleteResult.ORDER_REJECTED);
            return;
        }
        Workflow.await(() -> this.pizzaPrepared != null);
        this.flowStatus.setLastActivity("preparePizza");
        this.flowStatus.setCompleteResult(CompleteResult.PIZZA_PREPARED);
    }

    @Override
    public void approve(boolean approved, ObjectNode approveOrderRequest) {
        this.approved = approved;
        this.approveOrderRequest = approveOrderRequest;
    }

    @Override
    public void pizzaPrepared() {
        this.pizzaPrepared = new Object();
    }

    @Override
    public FlowStatus getStatus() {
        return flowStatus;
    }
}
