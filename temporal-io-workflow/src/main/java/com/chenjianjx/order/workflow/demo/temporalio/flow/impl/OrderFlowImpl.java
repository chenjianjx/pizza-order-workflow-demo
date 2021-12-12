package com.chenjianjx.order.workflow.demo.temporalio.flow.impl;

import com.chenjianjx.order.workflow.demo.temporalio.flow.OrderFlow;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


@Slf4j
public class OrderFlowImpl implements OrderFlow {
    @Override
    public void acceptOrder(UUID orderId) {
      log.info("acceptOrder called");
    }
}
