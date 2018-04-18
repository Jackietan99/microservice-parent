package com.jsfd.microservice.auth.spring.security.conf.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KaptchaServletProperties {

	@Value("${kaptcha.border}") 
    private String border;  

	@Value("${kaptcha.session.key}")  
    private String skey;  
      
    @Value("${kaptcha.textproducer.font.color}")  
    private String fcolor;  
      
    @Value("${kaptcha.textproducer.font.size}")  
    private String fsize;  
      
    @Value("${kaptcha.obscurificator.impl}")  
    private String obscurificator;  
      
    @Value("${kaptcha.noise.impl}")  
    private String noise;  
      
    @Value("${kaptcha.image.width}")  
    private String width;  
      
    @Value("${kaptcha.image.height}")  
    private String height;  
      
    @Value("${kaptcha.textproducer.char.length}")  
    private String clength;  
      
    @Value("${kaptcha.textproducer.char.space}")  
    private String cspace;  
      
    @Value("${kaptcha.background.clear.from}")  
    private String from;  
      
    @Value("${kaptcha.background.clear.to}")  
    private String to;
    
    /** 允许无验证码登录次数.*/
    @Value("${kaptcha.allow.nocode.login.number}")  
    private String allowNocodeLoginNumber;

	public String getAllowNocodeLoginNumber() {
		return allowNocodeLoginNumber;
	}

	public void setAllowNocodeLoginNumber(String allowNocodeLoginNumber) {
		this.allowNocodeLoginNumber = allowNocodeLoginNumber;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getFcolor() {
		return fcolor;
	}

	public void setFcolor(String fcolor) {
		this.fcolor = fcolor;
	}

	public String getFsize() {
		return fsize;
	}

	public void setFsize(String fsize) {
		this.fsize = fsize;
	}

	public String getObscurificator() {
		return obscurificator;
	}

	public void setObscurificator(String obscurificator) {
		this.obscurificator = obscurificator;
	}

	public String getNoise() {
		return noise;
	}

	public void setNoise(String noise) {
		this.noise = noise;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getClength() {
		return clength;
	}

	public void setClength(String clength) {
		this.clength = clength;
	}

	public String getCspace() {
		return cspace;
	}

	public void setCspace(String cspace) {
		this.cspace = cspace;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
