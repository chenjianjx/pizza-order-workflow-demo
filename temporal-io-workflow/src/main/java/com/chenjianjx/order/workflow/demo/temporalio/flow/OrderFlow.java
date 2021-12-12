package com.chenjianjx.order.workflow.demo.temporalio.flow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

@WorkflowInterface
public interface OrderFlow {

    @WorkflowMethod
    void acceptOrder(UUID orderId);
}


