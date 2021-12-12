package com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.temporal;

import com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.OrderFlowService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;


@Service
public class OrderFlowServiceTemporalImpl implements OrderFlowService {

    @Value("${temporal.server.address}")
    String temporalServerAddress;

    @Value("${temporal.task.queue}")
    String taskQueue;

    private WorkflowClient temporalClient;


    @PostConstruct
    public void init() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServerAddress).build());
        temporalClient = WorkflowClient.newInstance(service);
    }

    @Override
    public void acceptOrder(UUID orderId, ObjectNode createOrderRequest) {
        OrderFlow orderFlow = getWorkflowStub(orderId);
        WorkflowClient.start(orderFlow::acceptOrder, orderId, createOrderRequest);
    }

    private OrderFlow getWorkflowStub(UUID orderId) {
        WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                .setTaskQueue(taskQueue)
                .setWorkflowId(orderId.toString())
                .build();
        OrderFlow orderFlow = temporalClient.newWorkflowStub(OrderFlow.class, workflowOptions);
        return orderFlow;
    }
}
