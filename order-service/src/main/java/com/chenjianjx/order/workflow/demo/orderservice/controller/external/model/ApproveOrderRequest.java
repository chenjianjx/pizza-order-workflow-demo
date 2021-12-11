package com.chenjianjx.order.workflow.demo.orderservice.controller.external.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ApproveOrderRequest {
    private UUID orderId;
    /**
     * false means denied
     */
    private boolean approved;
    private String reason;
    /**
     * the name of an restaurant employee
     */
    private String approver;
}
