package com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.temporal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;


/**
 * should mirror interface "OrderFlow" in temporal-io-workflow.
 */
@WorkflowInterface
public interface OrderFlow {

    /**
     * @param orderId
     * @param createOrderRequest just used to echo back to order-service
     */
    @WorkflowMethod
    void acceptOrder(UUID orderId, ObjectNode createOrderRequest);

    /**
     * @param approveOrderRequest just used to echo back to order-service
     */
    @SignalMethod
    void approve(boolean approved, ObjectNode approveOrderRequest);

    @SignalMethod
    void pizzaPrepared();

    @QueryMethod
    ObjectNode getStatus();
}
