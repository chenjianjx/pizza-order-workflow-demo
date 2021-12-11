package com.chenjianjx.order.workflow.demo.orderservice.controller.internal.model;

import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.ApproveOrderRequest;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class SendRejectionEmailRequest {

    private UUID orderId;
    /**
     * json of the original {@link  ApproveOrderRequest}
     */
    private ObjectNode originApproveOrderRequest;

}
