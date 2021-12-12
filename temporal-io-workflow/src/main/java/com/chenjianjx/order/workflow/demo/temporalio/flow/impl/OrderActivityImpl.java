package com.chenjianjx.order.workflow.demo.temporalio.flow.impl;

import com.chenjianjx.order.workflow.demo.temporalio.flow.OrderActivity;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class OrderActivityImpl implements OrderActivity {

    private static OrderActivityImpl instance;

    private OrderActivityImpl(){}

    public static OrderActivityImpl getInstance(){
        if(null == instance){
            instance = new OrderActivityImpl();
        }
        return instance;
    }

    @Override
    public void processOrder(UUID orderId) {
      log.info("processOrder called");
    }
}
