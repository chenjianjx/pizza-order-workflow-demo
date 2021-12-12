package com.chenjianjx.order.workflow.demo.orderservice.controller.external.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    private UUID orderId;
}
