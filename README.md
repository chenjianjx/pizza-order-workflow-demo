# pizza-order-workflow-demo
Demo integration between a microservice and different workflow engines (currently only temporal.io) 

## Biz Requirements

Implement the pizza order process from [camunda doc](https://camunda.com/blog/2015/06/build-your-own-camunda-task-explorer/):

![pizz-order-image](./doc/pizza-order.png)


## System Architecture

![architecture](./doc/architecture.drawio.png)

### Biz service is facade

External services will invoke the `Order Service` using parameters like `orderId`. They know nothing about workflow engine.

### Order workflow engine focuses on `flow` 

The workflow engine knows little about order's biz logic. 
* It just invokes the logic in `Order Service`, as it is an orchestrator
* It won't store order data by itself

### Note: in real life there may be other biz services
There may be `Item Service`, `Payment Service` etc.   They will also interact with the workflow engine and carry out workflow activities.

For simplicity in this demo `Order Service` is the only biz service.




## Try it

### Prerequisites 

* Docker
* docker-composer
* JDK 11+

### Start the services

* Start workflow engine
    * If temporal-io is used, [start its services](temporal-io-workflow/start-services.md)
* [Start order-service](order-service/start-services.md)


### Execute workflows

See [Workflow Execution Showcase](doc/workflow-execution-showcase.md)

## How things work

Understand the details by reading code. There are some guidance below.

### Order Service Code
![order-service-code-map](order-service/code-map.drawio.png)

### Workflow engine artefacts

#### if temporal.io is used
![temporal-code-map](temporal-io-workflow/code-map.drawio.png)