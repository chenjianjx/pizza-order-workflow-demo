package com.chenjianjx.order.workflow.demo.temporalio.worker;

import com.chenjianjx.order.workflow.demo.temporalio.flow.impl.OrderActivityImpl;
import com.chenjianjx.order.workflow.demo.temporalio.flow.impl.OrderFlowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WorkerStarter {

    @Value("${temporal.server.address}")
    String temporalServerAddress;

    @PostConstruct
    @Profile("!test")
    public void start() {

        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServerAddress).build());
        WorkflowClient workflowClient = WorkflowClient.newInstance(service);

        WorkerFactory workerFactory = WorkerFactory.newInstance(workflowClient);
        Worker worker = workerFactory.newWorker("ORDER_TASK_QUEUE");

        worker.registerWorkflowImplementationTypes(OrderFlowImpl.class);
        worker.registerActivitiesImplementations(OrderActivityImpl.getInstance());

        workerFactory.start();
    }
}
