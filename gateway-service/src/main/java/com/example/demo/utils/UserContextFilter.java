package com.example.demo.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class UserContextFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest =(HttpServletRequest) request;
		UserContextHolder.getContext().setCorrelationId(UserContext.CORRELATION_ID);
		UserContextHolder.getContext().setUserId(UserContext.USER_ID);
		UserContextHolder.getContext().setSchoolId(UserContext.SCHOOL_ID);
		UserContextHolder.getContext().setAuthToken(UserContext.AUTH_TOKEN);
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
