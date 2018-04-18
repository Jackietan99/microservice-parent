package com.jsfd.microservice.auth.spring.security;

import com.lottery.auth.domain.LoginLog;
import com.lottery.auth.service.ILoginLogService;
import com.lottery.auth.spring.security.conf.properties.KaptchaServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @see SavedRequestAwareAuthenticationSuccessHandler
 * @ClassName:LoginSuccessHandlerImpl
 * @Description: TODO(登录成功处理)
 * @author:"czlxming".
 * @CreateDate:2017年9月29日-上午9:56:39.
 */
public class LoginAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	public static final String SESSION_KEY_USER_INFO = "_session_user_info";
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	private KaptchaServletProperties properties;
	
	@Autowired
	private ILoginLogService  loginLogService;
	
	@Override  
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,  ServletException {  
		logger.info("LoginSuccessHandler#onAuthenticationSuccess");
		Integer allowFailureNumber = Integer.valueOf(properties.getAllowNocodeLoginNumber());
		
		HttpSession session = request.getSession(false);
		session.setAttribute(LoginAuthenticationFailureHandlerImpl._isJcaptchaSessionKey, false);
		session.setAttribute(LoginAuthenticationFailureHandlerImpl._failureNumberSessionKey, allowFailureNumber);
		
		//Integer allowFailureNumber = Integer.valueOf(SystemConfig.getProperty(CaptchaAuthenticationFilter._loginNumberPropertiesKey));
		//session.setAttribute(CaptchaAuthenticationFilter._isJcaptchaSessionKey, false);
		//session.setAttribute(CaptchaAuthenticationFilter._failureNumberSessionKey, allowFailureNumber);
		
		//获得授权后可得到用户信息   可使用SUserService进行数据库操作
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();  
		String loginIp = getIpAddress(request);
		userDetails.setLoginIp(loginIp);
		session.setAttribute(SESSION_KEY_USER_INFO, userDetails);
		
		/*
		//System.out.println("管理员 " + userDetails.getTypeCode() + " 登录");  
		//输出登录提示信息  
        System.out.println("管理员 " + userDetails.getUsername() + " 登录");  
        System.out.println("IP :"+getIpAddress(request));
		 */
        /*
        super.setDefaultTargetUrl("/index");
        super.onAuthenticationSuccess(request, response, authentication);  
        */
		
		saveLoginLog(userDetails);
		
        String targetUrl = "/" + userDetails.getTypeCode(); // +"/index"
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        
    }  
    
    private void saveLoginLog(UserDetailsImpl userDetails) {
    	LoginLog loginLog = new LoginLog();
    	loginLog.setUserId(Long.valueOf(userDetails.getId()));
    	loginLog.setUsername(userDetails.getUsername());
    	loginLog.setLoginIp(userDetails.getLoginIp());
    	loginLog.setLoginTime(new Date(System.currentTimeMillis()));
    	loginLog.setLogType(1);
    	loginLogService.save(loginLog);
	}

	public String getIpAddress(HttpServletRequest request){    
        String ip = request.getHeader("x-forwarded-for");    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("WL-Proxy-Client-IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_CLIENT_IP");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
        }    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
            ip = request.getRemoteAddr();    
        }    
        return ip;    
    }

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	} 
	
}

