package com.prasad.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@Service
@RefreshScope
public class SchoolServiceDelegate {

	@Value("${msg:This is default Value}")
	String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod="getStudentDataDelegate_fallBack",
					threadPoolKey="schoolServicePool",
					threadPoolProperties= {
							@HystrixProperty(name="coreSize", value="30"),
							@HystrixProperty(name="maxQueueSize", value="10")
					},
					commandProperties= {
							@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
							@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
							@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000"),
							@HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
							@HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
					})
	public List<Object> getStudentDataDelegate(String schoolName) {
		System.out.println("School Service:Getting School details for " + schoolName);
		
		ResponseEntity<List<Object>> response=restTemplate.exchange("http://student-service/rest/student/"+schoolName, HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {
		});
		
		System.out.println(response);
		List<Object> responseLst=response.getBody();
		responseLst.add(message);
		return responseLst;
		
		
	}
	
	public List<Object> getStudentDataDelegate_fallBack(String schoolName) {
		
		System.out.println("Student Service is down!!! fallback route enabled...");
		List<Object> lst=new ArrayList<Object>();
		lst.add(message);
		lst.add("CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment.Service will be back shortly " + new Date());
				 
        return lst;
		
	}
}
