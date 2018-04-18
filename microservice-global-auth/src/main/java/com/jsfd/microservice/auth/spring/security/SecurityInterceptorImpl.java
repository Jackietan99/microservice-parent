package com.jsfd.microservice.auth.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * @see org.springframework.security.web.access.intercept.FilterSecurityInterceptor
 * @ClassName:SecurityInterceptorImpl
 * @Description: TODO(授权安全拦截器)
 * @author:"czlxming".
 * @CreateDate:2017年8月28日-下午9:58:23.
 */
public class SecurityInterceptorImpl extends AbstractSecurityInterceptor implements Filter {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	/** 安全元数据资源 .*/
	@Autowired
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	/** 决策管理器 .*/
	@Autowired
	private AccessDecisionManager accessDecisionManager;
	
	/** 认证管理器 .*/
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostConstruct  
    public void init(){  
        super.setAuthenticationManager(authenticationManager);  //org.springframework.security.authentication.ProviderManager
        super.setAccessDecisionManager(accessDecisionManager);  //com.twodreams.elevator.auth.spring.security.AccessDecisionManagerImpl 自定义决策管理器
    }
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	
	/** 取安全元数据资源 .*/ 
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		//log.debug("取安全元数据资源");
		return this.securityMetadataSource;
	}
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("filter===========================init");
	}

	@Override
	public void destroy() {
		log.debug("filter===========================destroy");
	}
}
