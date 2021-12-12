package com.chenjianjx.order.workflow.demo.temporalio.flow.impl;

import com.chenjianjx.order.workflow.demo.temporalio.flow.OrderActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class OrderActivityImpl implements OrderActivity {

    @Value("${orderService.internal.endpoint}")
    String orderServiceEndpoint;

    @Autowired
    OkHttpClient httpClient;

    @Autowired
    ObjectMapper objectMapper;

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    public void persistNewOrder(UUID orderId, ObjectNode createOrderRequest) {
        try {
            ObjectNode payload = objectMapper.createObjectNode();
            payload.put("orderId", orderId.toString());
            payload.set("originCreateOrderRequest", createOrderRequest);

            Request request = new Request.Builder()
                    .url(orderServiceEndpoint + "/persist-new-order")
                    .post(RequestBody.create(objectMapper.writeValueAsString(payload), JSON))
                    .build();
            try (Response response = httpClient.newCall(request).execute()) {
                int code = response.code();
                String body = response.body().string();
                if (code != 200) {
                    throw new RuntimeException("Order service returns " + code + " with body " + body);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
