package com.chenjianjx.order.workflow.demo.orderservice.controller.internal;

import com.chenjianjx.order.workflow.demo.orderservice.biz.OrderRepository;
import com.chenjianjx.order.workflow.demo.orderservice.biz.entity.Order;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.ApproveOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.internal.model.PersistNewOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.internal.model.SendRejectionEmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/internal")
@Slf4j
public class InternalController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    @PostMapping("/persist-new-order")
    public void persistNewOrder(@RequestBody PersistNewOrderRequest request) {
        log.info("Received 'persistNewOrder' from workflow engine with orderId {}", request.getOrderId());
        CreateOrderRequest createOrderRequest;
        try {
            createOrderRequest =
                    objectMapper.treeToValue(request.getOriginalCreateOrderRequest(), CreateOrderRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Order order = Order.builder()
                .id(request.getOrderId())
                .createdAt(LocalDateTime.now())
                .pizzaType(createOrderRequest.getPizzaType())
                .customerEmail(createOrderRequest.getCustomerEmail())
                .build();

        orderRepository.insert(order);
    }

    @PostMapping("/send-rejection-email")
    public void sendRejectionEmail(@RequestBody SendRejectionEmailRequest request) {
        log.info("Received 'sendRejectionEmail' from workflow engine with orderId {}", request.getOrderId());
        ApproveOrderRequest approveOrderRequest;
        try {
            approveOrderRequest =
                    objectMapper.treeToValue(request.getOriginalApproveOrderRequest(), ApproveOrderRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        UUID orderId = request.getOrderId();
        Order order = orderRepository.getOrderById(orderId);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("service@fake-restaurant.com");
        msg.setTo(order.getCustomerEmail());

        msg.setSubject("Your order is rejected");
        msg.setText("Your order " + order.getId() + " has been rejected. Reason: " + approveOrderRequest.getReason());

        javaMailSender.send(msg);
    }
}
