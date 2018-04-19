package com.jsfd.microservice.auth.spring.security;


import com.jsfd.microservice.auth.pojo.Perm;
import com.jsfd.microservice.auth.pojo.User;
import com.jsfd.microservice.auth.service.IPermService;
import com.jsfd.microservice.auth.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService, MessageSourceAware {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private IUserService userService;
	@Autowired
	private IPermService permService;
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	private boolean enableAuthorities = true;
	//org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
	//org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#abstract UserDetails retrieveUser
	//org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername");
		User user = userService.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(this.messages.getMessage("UserDetailsServiceImpl.notFound",new Object[] { username }, "Username {0} not found"));
		}
		
		Collection<GrantedAuthority> dbAuthsSet = loadUserAuthorities(user.getId());
		if (this.enableAuthorities) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getId()));
		}
		//dbAuthsSet:[role, userstatus, groupinfo, groupstruct, authconf, admin, acl, form, userrepo, scope, userinfo, bpm, userattr]
		return new UserDetailsImpl(user,dbAuthsSet);
	}
	
	/** 获得用户的权限 . */
	protected Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();
		List<Perm> perms = permService.findByUserId(userId);
		for (Perm perm : perms) {
			GrantedAuthority authority = new SimpleGrantedAuthority(perm.getCode());
			dbAuthsSet.add(authority);
		}
		return dbAuthsSet;
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		Assert.notNull(messageSource, "messageSource cannot be null");
		this.messages = new MessageSourceAccessor(messageSource);
	}
	
}
