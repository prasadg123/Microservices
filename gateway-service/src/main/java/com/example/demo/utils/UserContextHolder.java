package com.example.demo.utils;

public class UserContextHolder {

	public static final ThreadLocal<UserContext> userContext=new ThreadLocal<UserContext>();
	
	public static final UserContext getContext() {
		UserContext context=userContext.get();
		if(context==null) {
			context=createEmptyContext();
			userContext.set(context);
		}
		return userContext.get();
	}
	
	

	private static final UserContext createEmptyContext() {
		// TODO Auto-generated method stub
		return new UserContext();
	}
	
	
}
