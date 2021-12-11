package com.chenjianjx.order.workflow.demo.orderservice.controller.external.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderRequest {
    private String pizzaType;
    private String customerEmail;
}
