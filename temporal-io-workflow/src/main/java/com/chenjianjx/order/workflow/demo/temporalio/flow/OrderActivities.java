package com.chenjianjx.order.workflow.demo.temporalio.flow;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.UUID;

@ActivityInterface
public interface OrderActivities {

    @ActivityMethod
    void persistNewOrder(UUID orderId, ObjectNode createOrderRequest);
}
