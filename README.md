# pizza-order-workflow-demo
A sample order restful api based on a replaceable workflow engine (currently only temporal.io) 

The pizza order process is from [camunda](https://camunda.com/blog/2015/06/build-your-own-camunda-task-explorer/):

![pizz-order-image](./doc/pizza-order.png)


## System Architecture

![architecture](./doc/architecture.drawio.png)

### Order service is the facade

External services will invoke the `Order Service` using parameters like `orderId`. They know nothing about workflow engine.

### Order workflow engine focuses on `flow` 

The workflow knows little about order's biz logic. 
* It just invokes the logic in `Order Service`
* It won't store order data itself


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