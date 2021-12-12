package com.chenjianjx.order.workflow.demo.temporalio.flow;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.UUID;

@ActivityInterface
public interface OrderActivity {

    @ActivityMethod
    void processOrder(UUID orderId);
}
