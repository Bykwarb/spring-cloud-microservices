package com.example.licenseservice.utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserContextInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        requestTemplate.header(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());

    }
}
