package com.example.demo.utils;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;


public class UserContextInterceptor implements ClientHttpRequestInterceptor{

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers=request.getHeaders();
		headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
		headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
		System.out.println(headers);
		return execution.execute(request, body);
	}

}
