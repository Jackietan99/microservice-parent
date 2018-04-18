package com.jsfd.microservice.auth.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * @see org.springframework.security.access.vote.AbstractAccessDecisionManager
 * @ClassName:AccessDecisionManagerImpl
 * @Description: TODO(自定义决策管理器)
 * @author:"czlxming".
 * @CreateDate:2017年8月27日-下午10:43:51.
 */
public class AccessDecisionManagerImpl implements AccessDecisionManager {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	/** 决策：此方法不抛异常即通过.*/
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		log.debug("决策");
		if (configAttributes == null) {
			// 当前URL是不需要权限控制的资源url,直接通过
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String authorityId = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (authorityId.trim().equals(ga.getAuthority().trim())) {
					//用户有访问资源的权限
					return;
				}
			}
		}
		throw new AccessDeniedException("没有访问权限!");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
