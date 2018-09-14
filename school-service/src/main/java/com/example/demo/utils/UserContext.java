package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

	public static final String CORRELATION_ID="tmx-correlation-id";
	public static final String AUTH_TOKEN="tmx-auth-token";
	public static final String USER_ID="tmx-user-id";
	public static final String SCHOOL_ID="tmx-school-id";
	
	private String correlationId;
	private String 	authToken;
	private String userId;
	private String schoolId;
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
}
