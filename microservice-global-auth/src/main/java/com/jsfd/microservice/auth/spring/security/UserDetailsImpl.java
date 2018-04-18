package com.jsfd.microservice.auth.spring.security;

import com.lottery.auth.domain.User;
import com.lottery.auth.domain.assist.UserTypeEnum;
import com.lottery.core.util.DateUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

public class UserDetailsImpl implements UserDetails, CredentialsContainer {
	
	private static final long serialVersionUID = 1L;

	/** 用户名. */
	private String username;
	/** 密码. */
	private String password;
	/** 用户ID. */
	private String id;
	/** 手机号码. */
	private String mobile;
	/** 邮箱. */
	private String email;
	/** 昵称. */
	private String nickName;
	/** 语言. */
	private String language;
	/** 用户类型 . */
	private Integer type;
	/** 用户类型Code. */
	private String typeCode;
	/** 最后登录IP . */
    private String loginIp;
	/** 截止期限 . */
    private String deadline; 
    /** 最后登录时间 . */
	private String lastLogin;
	/** 引用外部用户对象ID */
	private String userRefId;
	/** 是否有效 .*/
	private Boolean isEnabled = Boolean.TRUE;
	/**  账号是否未过期 .*/
	private  boolean accountNonExpired = Boolean.TRUE;
	/** 账号是否未锁定 .*/
	private  boolean accountNonLocked = Boolean.TRUE;
	/** 账号凭证是否未过期 .*/
	private  boolean credentialsNonExpired = Boolean.TRUE;
	
	/** 授权, 权限, 角色集合 . */
	private  Set<GrantedAuthority> authorities;
	
	public UserDetailsImpl() {
		super();
	}
	
	public UserDetailsImpl(User user, Collection<? extends GrantedAuthority> authorities) {
		this.id =  String.valueOf(user.getId());
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.mobile = user.getMobile();
		this.email = user.getEmail();
		this.nickName = user.getNickName();
		this.language = user.getLanguage();
		this.type = user.getUserType();
		this.setUserRefId(user.getUserRefId());
		//this.typeCode = user.getUserType().name().toLowerCase();
		
		if(user.getUserType() == UserTypeEnum.ROOT.ordinal()) { // 0
			this.typeCode = "root";
		}else if(user.getUserType() == UserTypeEnum.ADMIN.ordinal()) { //1
			this.typeCode = "admin";
		}else if(user.getUserType() == UserTypeEnum.AGENT.ordinal()) {//2
			this.typeCode = "agent";
		}else if(user.getUserType() == UserTypeEnum.MEMBER.ordinal()) {//3
			this.typeCode = "member";
		}
		this.loginIp = user.getLoginIp();
		this.deadline = DateUtils.format(user.getDeadline(),"yyyy-MM-dd");
		this.lastLogin = DateUtils.format(user.getLastLogin(),"yyyy-MM-dd");
		this.isEnabled = user.getIsEnabled();
		
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	@Override
	public void eraseCredentials() {
		password = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());
		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}
		return sortedAuthorities;
	}
	
	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		public int compare(GrantedAuthority ga1, GrantedAuthority ga2) {
			if (ga2.getAuthority() == null) {
				return -1;
			}
			if (ga1.getAuthority() == null) {
				return 1;
			}
			return ga1.getAuthority().compareTo(ga2.getAuthority());
		}
	}

	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof UserDetailsImpl) {
			return username.equals(((UserDetailsImpl) rhs).getUsername());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.isEnabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

		if (!authorities.isEmpty()) {
			sb.append("Granted Authorities: ");
			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;
				sb.append(auth);
			}
		}
		else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getUserRefId() {
		return userRefId;
	}

	public void setUserRefId(String userRefId) {
		this.userRefId = userRefId;
	}
}
