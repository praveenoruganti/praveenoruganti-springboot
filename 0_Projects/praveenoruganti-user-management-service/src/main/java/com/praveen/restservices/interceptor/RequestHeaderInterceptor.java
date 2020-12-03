package com.praveen.restservices.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.praveen.restservices.exceptions.InvalidHeaderFieldException;

@Component
public class RequestHeaderInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

//		if (StringUtils.isBlank(request.getHeader("user-auth-key"))) {
//			throw new InvalidHeaderFieldException("Invalid request");
//		}

		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println("In RequestHeaderInterceptor  :: SPRING Called postHandle method for URI "+request.getRequestURI() );
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		
		System.out.println("In RequestHeaderInterceptor  :: SPRING Called afterCompletion method for URI "+request.getRequestURI() );
	}

}
