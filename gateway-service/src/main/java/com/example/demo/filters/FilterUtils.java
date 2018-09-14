package com.example.demo.filters;

import com.netflix.zuul.context.RequestContext;

public class FilterUtils {

	public static final String CORRELATION_ID="tmx-correlation-id";
	public static final String AUTH_TOKEN="tmx-auth-token";
	public static final String USER_ID="tmx-user-id";
	public static final String SCHOOL_ID="tmx-school-id";
	public static final String PRE_FILTER_TYPE="pre";
	public static final String POST_FILTER_TYPE="post";
	public static final String ROUTE_FILTER_TYPE="route";
	
	RequestContext ctx=RequestContext.getCurrentContext();
	
	public String getCorrelationId() {
		
		//RequestContext ctx=RequestContext.getCurrentContext();
		
		if(ctx.getRequest().getHeader(CORRELATION_ID)!=null) {
			return ctx.getRequest().getHeader(CORRELATION_ID);
		}
		else {
			return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
		}
		
	}
	
	public void setCorrelatioId(String correlationId) {
		//RequestContext ctx=RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
	}
	
	public String getSchoolId() {
		
		//RequestContext ctx=RequestContext.getCurrentContext();
		if(ctx.getRequest().getHeader(SCHOOL_ID)!=null) {
			return ctx.getRequest().getHeader(SCHOOL_ID);
		}
		else {
			return ctx.getZuulRequestHeaders().get(SCHOOL_ID);
		}
		
	}
	
	public void setSchoolId(String schoolId) {
		
		//RequestContext ctx=RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(SCHOOL_ID, schoolId);
	}
	
	public String getUserId() {
		
		//RequestContext ctx=RequestContext.getCurrentContext();
		if(ctx.getRequest().getHeader(USER_ID)!=null) {
			return ctx.getRequest().getHeader(USER_ID);
		}
		else {
			return ctx.getZuulRequestHeaders().get(USER_ID);
		}
		
	}
	
	public void setUserId(String userId) {
		ctx.addZuulRequestHeader(USER_ID, userId);
	}
	
	public String getServiceId() {
		
		if(ctx.get("service")==null) return "";
		else return ctx.get("serviceid").toString();
	}
}
