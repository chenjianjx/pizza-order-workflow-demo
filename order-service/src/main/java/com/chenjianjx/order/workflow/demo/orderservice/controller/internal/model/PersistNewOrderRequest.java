package com.chenjianjx.order.workflow.demo.orderservice.controller.internal.model;

import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderRequest;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PersistNewOrderRequest {

    private UUID orderId;
    /**
     * json of the original {@link CreateOrderRequest}
     */
    private ObjectNode originalCreateOrderRequest;
}
