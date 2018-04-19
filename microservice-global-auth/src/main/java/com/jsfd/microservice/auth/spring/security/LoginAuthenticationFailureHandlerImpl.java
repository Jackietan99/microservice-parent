package com.jsfd.microservice.auth.spring.security;

import com.jsfd.microservice.auth.spring.security.conf.properties.KaptchaServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 
 * @ClassName:LoginAuthenticationFailureHandlerImpl
 * @Description: TODO(登录错误处理,限制无验证码登录次数)
 * @author:"jackchen".
 * @CreateDate:2017年10月22日-上午3:29:57.
 */
public class LoginAuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
	/** 允许无验证码登录次数.*/
	public static final String _allowNoCodeLoginNumber = "systemConfig.login.allowNoCodeLoginNumber";
	/** 登录错误数session键.*/
	public static final String _failureNumberSessionKey = "failureNumber";
	/** 是否启用验证码session键.*/
	public static final String _isJcaptchaSessionKey = "isJcaptcha";
	
	@Autowired
	private KaptchaServletProperties properties;
	
	public LoginAuthenticationFailureHandlerImpl(String defaultFailureUrl) {
		setDefaultFailureUrl(defaultFailureUrl);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if(session.getAttribute(_failureNumberSessionKey) == null) {
			session.setAttribute(_failureNumberSessionKey, Integer.valueOf(Integer.valueOf(properties.getAllowNocodeLoginNumber())));
		}
		Integer failureNumber = (Integer) session.getAttribute(_failureNumberSessionKey);
        session.setAttribute(_failureNumberSessionKey, failureNumber - 1);
        if(failureNumber == 1) {
        	session.setAttribute(_isJcaptchaSessionKey, true);
        }
		super.onAuthenticationFailure(request, response, exception);
	}

}
