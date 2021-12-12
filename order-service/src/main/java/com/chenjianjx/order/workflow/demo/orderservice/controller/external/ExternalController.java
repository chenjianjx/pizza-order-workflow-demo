package com.chenjianjx.order.workflow.demo.orderservice.controller.external;

import com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.OrderFlowService;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.ApproveOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    ObjectMapper objectMapper;

    /**
     * From customer system
     *
     * @param request
     * @return the order Id
     */
    @PostMapping
    public UUID createOrder(@RequestBody CreateOrderRequest request) {
        UUID orderId = UUID.randomUUID();
        orderFlowService.acceptOrder(orderId, objectMapper.valueToTree(request));
        log.info("Sent 'acceptOrder' to workflow engine with orderId {}", orderId);
        return orderId;
    }

    /**
     * From restaurant admin console
     *
     * @param request
     */
    @PutMapping
    public void approveOrder(@RequestBody ApproveOrderRequest request) {
        //TODO send a signal to workflow
        log.info("Sent 'approveOrder' to workflow engine with orderId {}", request.getOrderId());
    }


//    @GetMapping("/{orderId}")
//    public Order getOrder(@PathVariable("orderId") UUID orderId) {
//        return null;
//    }
}
