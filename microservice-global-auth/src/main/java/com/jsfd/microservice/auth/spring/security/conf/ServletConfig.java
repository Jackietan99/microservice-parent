package com.jsfd.microservice.auth.spring.security.conf;


import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.jsfd.microservice.auth.spring.security.conf.properties.KaptchaServletProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;

@Configuration
public class ServletConfig {

	@Bean
	public ServletRegistrationBean kaptchaServletRegistrationBean(KaptchaServletProperties properties) throws ServletException {
		// return new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha/obtain");
		ServletRegistrationBean servlet  = new ServletRegistrationBean();
		servlet.setServlet(new KaptchaServlet());
		servlet.addUrlMappings("/kaptcha/obtain");
		//设置属性   @see com.google.code.kaptcha.Constants
		servlet.addInitParameter("kaptcha.border", properties.getBorder() != null ? properties.getBorder() : "no");//无边框  
        servlet.addInitParameter("kaptcha.session.key", properties.getSkey());//session key  
        servlet.addInitParameter("kaptcha.textproducer.font.color", properties.getFcolor());  
        servlet.addInitParameter("kaptcha.textproducer.font.size", properties.getFsize());  
        servlet.addInitParameter("kaptcha.obscurificator.impl", properties.getObscurificator());  
        servlet.addInitParameter("kaptcha.noise.impl", properties.getNoise());  
        servlet.addInitParameter("kaptcha.image.width", properties.getWidth());  
        servlet.addInitParameter("kaptcha.image.height", properties.getHeight());  
        servlet.addInitParameter("kaptcha.textproducer.char.length", properties.getClength()); //验证码位数 
        servlet.addInitParameter("kaptcha.textproducer.char.space", properties.getCspace());  
        servlet.addInitParameter("kaptcha.background.clear.from", properties.getFrom()); //和登录框背景颜色一致   
        servlet.addInitParameter("kaptcha.background.clear.to", properties.getTo()); 

		return servlet;

	}

}
