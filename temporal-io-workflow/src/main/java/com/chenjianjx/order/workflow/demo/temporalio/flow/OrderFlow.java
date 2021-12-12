package com.chenjianjx.order.workflow.demo.temporalio.flow;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

@WorkflowInterface
public interface OrderFlow {

    @WorkflowMethod
    void acceptOrder(UUID orderId, ObjectNode createOrderRequest);
}


