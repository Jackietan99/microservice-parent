package com.jsfd.microservice.auth.spring.security;

import com.lottery.auth.spring.security.conf.properties.KaptchaServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @ClassName: LoginAuthenticationFilter
 * @Description: TODO(验证码登录认证和无验证码登录次数过滤器 ,限制无验证码登录次数 , 此功能由 LoginAuthenticationFailureHandlerImpl完成)
 * @author:"czlxming".
 * @CreateDate:2017年8月27日-下午11:59:38.
 */
public class CaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	/** 验证码ServletRequest键.*/
	public static final String _authCodeKey = "authCode";
	/** 允许无验证码登录次数.*/
	public static final String _allowNoCodeLoginNumber = "systemConfig.login.allowNoCodeLoginNumber";
	/** 登录错误数session键.*/
	public static final String _failureNumberSessionKey = "failureNumber";
	/** 是否启用验证码session键.*/
	public static final String _isJcaptchaSessionKey = "isJcaptcha";
	
	/** @see com.twodreams.elevator.conf.WebSecurityConfig#captchaAuthenticationFilter() 。*/
	private String servletPath; 
	
	@Autowired
	private KaptchaServletProperties properties;
	
	public CaptchaAuthenticationFilter(String servletPath,String failureUrl) {
		super(servletPath);  
        this.servletPath=servletPath;  
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl)); 
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse res = (HttpServletResponse) response; 
        if ("POST".equalsIgnoreCase(req.getMethod()) && servletPath.equals(req.getServletPath())){  
        	
        	//限制无验证码登录次数 , 此功能由 LoginAuthenticationFailureHandlerImpl完成
        	//setLoginNumber(req);
        	
        	String sessionCode = (String) req.getSession().getAttribute(properties.getSkey());
        	String authCode = req.getParameter(_authCodeKey);
            if(sessionCode != null && !sessionCode.equalsIgnoreCase(authCode)){  
                unsuccessfulAuthentication(req, res, new InsufficientAuthenticationException("输入的验证码不正确"));  
                return;  
            }
        } 

        chain.doFilter(request,response);  
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
