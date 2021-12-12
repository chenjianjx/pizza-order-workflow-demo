package com.chenjianjx.order.workflow.demo.temporalio.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OkHttpClient httpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

}
