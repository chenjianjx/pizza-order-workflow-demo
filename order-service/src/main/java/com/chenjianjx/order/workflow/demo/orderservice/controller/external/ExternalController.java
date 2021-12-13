package com.chenjianjx.order.workflow.demo.orderservice.controller.external;

import com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.OrderFlowService;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.ApproveOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/orders")
public class ExternalController {

    @Autowired
    OrderFlowService orderFlowService;

    /**
     * From customer system
     *
     * @param request
     */
    @PostMapping
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        UUID orderId = UUID.randomUUID();
        orderFlowService.acceptOrder(orderId, request);
        log.info("Sent 'acceptOrder' to workflow engine with orderId {}", orderId);
        return CreateOrderResponse.builder().orderId(orderId).build();
    }

    @GetMapping("/{orderId}/status")
    public ObjectNode getOrderStatus(@PathVariable("orderId") UUID orderId) {
        return orderFlowService.getStatus(orderId);
    }

    /**
     * From restaurant admin console
     *
     * @param request
     */
    @PutMapping("/approve")
    public void approveOrder(@RequestBody ApproveOrderRequest request) {
        log.info("Received 'approveOrder' request with orderId {}", request.getOrderId());
        orderFlowService.approve(request);
        log.info("Sent 'approveOrder' to workflow engine with orderId {}", request.getOrderId());
    }

    /**
     * From restaurant admin console
     */
    @PutMapping("/pizza-prepared/{orderId}")
    public void pizzaPrepared(@PathVariable("orderId") UUID orderId) {
        log.info("Received 'pizzaPrepared' request with orderId {}", orderId);
        orderFlowService.pizzaPrepared(orderId);
        log.info("Sent 'pizzaPrepared' to workflow engine with orderId {}", orderId);
    }
}
