package com.chenjianjx.order.workflow.demo.orderservice.controller.external;

import com.chenjianjx.order.workflow.demo.orderservice.biz.entity.Order;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.ApproveOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class ExternalController {

    /**
     * From customer system
     *
     * @param request
     * @return the order Id
     */
    @PostMapping
    public UUID createOrder(@RequestBody CreateOrderRequest request) {
        //TODO start the workflow
        return UUID.randomUUID();
    }

    /**
     * From restaurant admin console
     *
     * @param request
     */
    @PutMapping
    public void approveOrder(@RequestBody ApproveOrderRequest request) {
        //TODO send a signal to workflow
    }


//    @GetMapping("/{orderId}")
//    public Order getOrder(@PathVariable("orderId") UUID orderId) {
//        return null;
//    }
}
