package com.prasad.demo;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import com.example.demo.utils.UserContextInterceptor;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableEurekaClient
@EnableResourceServer
@RefreshScope
@SpringBootApplication
public class SchoolServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}
	
	
	/*@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate=new RestTemplate();
		List interceptors=restTemplate.getInterceptors();
		if(interceptors==null) {
			restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		}
		return restTemplate;
	}*/
	@LoadBalanced
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(
	OAuth2ClientContext oauth2ClientContext,
	OAuth2ProtectedResourceDetails details) {
	return new OAuth2RestTemplate(details, oauth2ClientContext);
	}
	@Bean(name = "hystrixRegistrationBean")
	public ServletRegistrationBean servletRegistrationBean() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(
	            new HystrixMetricsStreamServlet(), "/hystrix.stream");
	    registration.setName("hystrixServlet");
	    registration.setLoadOnStartup(1);
	    return registration;
	}
}
