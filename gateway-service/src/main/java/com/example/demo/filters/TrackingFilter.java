package com.example.demo.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class TrackingFilter extends ZuulFilter {

	public static final int FILTER_ORDER=1;
	public static final boolean SHOULD_FILTER=true;
	
	private static final Logger logger=LoggerFactory.getLogger(TrackingFilter.class);
	
	@Autowired
	private FilterUtils filterUtils;
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return SHOULD_FILTER;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return filterUtils.PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FILTER_ORDER;
	}
	
	public boolean isCorrelationIdPresent() {
		if(filterUtils.getCorrelationId()!=null) {
			return true;
		}
		return false;
	}
	
	public String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}
	
	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		if(isCorrelationIdPresent()) {
			logger.info("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
		}
		else {
			filterUtils.setCorrelatioId(generateCorrelationId());
			logger.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
		}
		
		RequestContext ctx=RequestContext.getCurrentContext();
		logger.debug("Processing incoming request for {}.",  ctx.getRequest().getRequestURI());
		return null;
		
	}


}
