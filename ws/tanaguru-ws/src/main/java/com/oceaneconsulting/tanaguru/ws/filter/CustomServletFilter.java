package com.oceaneconsulting.tanaguru.ws.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import com.oceaneconsulting.tanaguru.entity.impl.WsInvocationImpl;
import com.oceaneconsulting.tanaguru.service.WsInvocationService;

@WebFilter(urlPatterns = {"/*" }, asyncSupported = true)
public class CustomServletFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(CustomServletFilter.class);
	
	WsInvocationService bean = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		if(bean == null){
			bean = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean(WsInvocationService.class);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			boolean access = Boolean.FALSE;
			//TODO 2 cases : audit page and other
			LOGGER.debug("Access control launched...");
//			access = bean.serviceAccesVerification(request.getRemoteHost(), request.getRemoteAddr());
			LOGGER.info("Audit page access control for host name ="+request.getRemoteHost()+", ip="+request.getRemoteAddr());
			
			//save user information
			WsInvocation invocation = new WsInvocationImpl();
			invocation.setDateInvocation(new Date());
			invocation.setHostIp(request.getRemoteAddr());
			invocation.setHostName(request.getRemoteHost());
			invocation.setAuditType(0);
			invocation.setUser(null);//unnecessary information
			bean.create(invocation);
			
			//verify user access limitation
			access = bean.checkLimitationOverflow(null, request.getRemoteHost(), request.getRemoteAddr(), null);
			
			LOGGER.debug("Verification complete, allowed =" + access);
			
			if(!access){
				 ((HttpServletResponse)response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "You reached the maximum number of audits allowed for today...");
			} else {
				LOGGER.debug("Request filter process...");
				chain.doFilter(request, response);
				LOGGER.debug("Request filter OK.");
			}

		} catch (Exception e) {
			LOGGER.debug(ExceptionUtils.getFullStackTrace(e));
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	@Override
	public void destroy() {
	}
}