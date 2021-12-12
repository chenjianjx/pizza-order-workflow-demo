package com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.temporal;

import com.chenjianjx.order.workflow.demo.orderservice.biz.workflow.OrderFlowService;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.ApproveOrderRequest;
import com.chenjianjx.order.workflow.demo.orderservice.controller.external.model.CreateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.temporal.api.workflowservice.v1.ListWorkflowExecutionsRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ObjectMapper objectMapper;

    private WorkflowClient temporalClient;


    @PostConstruct
    public void init() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServerAddress).build());
        temporalClient = WorkflowClient.newInstance(service);
    }

    @Override
    public void acceptOrder(UUID orderId, CreateOrderRequest createOrderRequest) {
        WorkflowOptions workflowOptions = WorkflowOptions.newBuilder()
                .setTaskQueue(taskQueue)
                .setWorkflowId(toWorkflowId(orderId))
                .build();
        OrderFlow orderFlow = temporalClient.newWorkflowStub(OrderFlow.class, workflowOptions);

        WorkflowClient.start(orderFlow::acceptOrder, orderId, objectMapper.valueToTree(createOrderRequest));
    }

    @Override
    public void approve(ApproveOrderRequest approveOrderRequest) {
        OrderFlow orderFlow = temporalClient.newWorkflowStub(OrderFlow.class, toWorkflowId(approveOrderRequest.getOrderId())); //retrieve a workflow
        orderFlow.approve(approveOrderRequest.isApproved(), objectMapper.valueToTree(approveOrderRequest));
    }

    @Override
    public void pizzaPrepared(UUID orderId) {
        OrderFlow orderFlow = temporalClient.newWorkflowStub(OrderFlow.class, toWorkflowId(orderId));
        orderFlow.pizzaPrepared();
        ListWorkflowExecutionsRequest s;
    }

    @Override
    public ObjectNode getStatus(UUID orderId) {
        OrderFlow orderFlow = temporalClient.newWorkflowStub(OrderFlow.class, toWorkflowId(orderId));
        ObjectNode status = orderFlow.getStatus();
        status.put("orderId", orderId.toString());
        return  status;
    }

    private String toWorkflowId(UUID orderId) {
        return "For_Order_" + orderId.toString();
    }
}
