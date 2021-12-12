package com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.temporal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;


/**
 * should mirror interface "OrderFlow" in temporal-io-workflow.
 */
@WorkflowInterface
public interface OrderFlow {

    @WorkflowMethod
    void acceptOrder(UUID orderId, ObjectNode createOrderRequest);
}
