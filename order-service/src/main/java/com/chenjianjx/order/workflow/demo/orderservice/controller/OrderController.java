package com.chenjianjx.order.workflow.demo.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/dummy")
    public String dummy() {
        return "ok";
    }
}
