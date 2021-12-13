# Workflow execution showcase

You will invoke public endpoints of `order-service` with `curl` 


## Create order

```
curl --location --request POST 'http://localhost:12110/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "pizzaType": "HAWAIIAN",
    "customerEmail": "test@not-exsting.com"
}'
```

You will see an orderId returned.  

```
{"orderId":"d12ff800-8305-459f-961f-285a682f1b0d"}
```

Go to workflow engine's console to see the created workflow 
  * If temporal.io is used, go to its web console http://localhost:8088 . The status will be "Running"

You can go to Mongo web UI http://localhost:12111/ to see an order has been `persisted`

## Get order status

```
curl http://localhost:12110/orders/d12ff800-8305-459f-961f-285a682f1b0d/status
```

Result is 

```
{
  "lastActivity": "persistNewOrder",
  "completeResult": null,
  "orderId": "d12ff800-8305-459f-961f-285a682f1b0d"
}
```


## Reject order

```
curl --location --request PUT 'http://localhost:12110/orders/approve' \
--header 'Content-Type: application/json' \
--data-raw '{
  "orderId": "d12ff800-8305-459f-961f-285a682f1b0d",
  "approved": false,
  "reason": "Cook has left",
  "approver": "David Beckham"
}'

```

Go to MailHog web http://localhost:12112/,  you should be able to see a rejection email. 

Go to workflow engine's console to see the workflow status
* If temporal.io is used, go to its web console http://localhost:8088 . The status will be "Completed" now


Query the status again. You will see

```
{
    "lastActivity": "sendRejectionEmail",
    "completeResult": "ORDER_REJECTED",
    "orderId": "d12ff800-8305-459f-961f-285a682f1b0d"
}
```

## Alternative flow : Approve order

### Create an order

Say the order this time is 
```
{
    "orderId": "33b1ddc5-2492-42f3-9718-39a014a4b418"
}
```


### Approve it

```
curl --location --request PUT 'http://localhost:12110/orders/approve' \
--header 'Content-Type: application/json' \
--data-raw '{
  "orderId": "33b1ddc5-2492-42f3-9718-39a014a4b418",
  "approved": true,
  "approver": "Jack London"
}'
```

After this your workflow is still `running` because the order is waiting for "pizza preparation"  

### Send "pizza prepared" event

```
curl --request PUT 'http://localhost:12110/orders/pizza-prepared/33b1ddc5-2492-42f3-9718-39a014a4b418'
```

Now query the status, you will see

```
{
    "lastActivity": "preparePizza",
    "completeResult": "PIZZA_PREPARED",
    "orderId": "33b1ddc5-2492-42f3-9718-39a014a4b418"
}
```