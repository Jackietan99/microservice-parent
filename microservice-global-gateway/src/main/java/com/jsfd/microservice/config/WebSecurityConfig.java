package com.jsfd.microservice.config;


import com.jsfd.microservice.auth.spring.security.*;
import com.jsfd.microservice.auth.spring.security.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 
	
	@Bean
	SecurityMetadataSourceImpl securityMetadataSource() {
		return new SecurityMetadataSourceImpl();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public AccessDecisionManagerImpl accessDecisionManager() {
		return new AccessDecisionManagerImpl();
	}
	
	@Bean
	public SecurityInterceptorImpl securityInterceptor() {
		SecurityInterceptorImpl securityInterceptor = new SecurityInterceptorImpl();
		securityInterceptor.setAccessDecisionManager(accessDecisionManager());
		return securityInterceptor;
	}
	
	@Bean
	public LoginAuthenticationSuccessHandlerImpl loginSuccessHandler() {
		return new LoginAuthenticationSuccessHandlerImpl();
	}

	@Bean
	public LoginAuthenticationFailureHandlerImpl loginFailureHandler() {
		return new LoginAuthenticationFailureHandlerImpl("/login?_error");
	}
	
	@Bean
	public AccessDeniedHandlerImpl accessDeniedHandler() {
		return new AccessDeniedHandlerImpl();
	}
	
    @Bean  
    public PasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder(Constants.PASSWORD_ENCODER_STRENGTH);
    } 

    @Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    //return new HttpSessionEventPublisher();
	    return new EnhancedHttpSessionEventPublisher();
	}
    
    @Bean  
    public SessionRegistry sessionRegistry(){  
        return new SessionRegistryImpl();  
    } 
	
	@Override  
    public AuthenticationManager authenticationManagerBean() throws Exception {  
		return super.authenticationManagerBean();  
    } 
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);   
    }
    
    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter() throws Exception {
    	CaptchaAuthenticationFilter filer = new CaptchaAuthenticationFilter("/login", "/login?error");
    	filer.setAuthenticationManager(authenticationManagerBean());
    	return filer;
    } 
    
    
    @Override  
    public void configure(WebSecurity web) throws Exception {  
        web.ignoring().antMatchers("/static/**", "/assets/**", "/favicon.ico");  
    }
	
	//LogoutSuccessHandler
	
	//@see org.springframework.security.config.annotation.web.configurers.LogoutConfigurer.getLogoutSuccessHandler()
	// org.springframework.security.web.authentication.WebAuthenticationDetails
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	http     
    		.addFilterBefore(captchaAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
    		.addFilterBefore(securityInterceptor(), FilterSecurityInterceptor.class) 
    		.authorizeRequests()
                .antMatchers("/", "/index", "/*.html","/lock", "/kaptcha/**", "/isJcaptcha/**", "/test/**", "/mobile/**", "/druid/**", "/templates/**", "/ui/**", "/api/**")
                	.permitAll().anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login") //用户登录页面
                .failureUrl("/login?_error")
                .failureHandler(loginFailureHandler())
                .successForwardUrl("/loginSuccess")
                .successHandler(loginSuccessHandler())
                .permitAll() 
                .and()
            .logout()
            	.logoutUrl("/logout")
            	.deleteCookies("remove")
            	//.logoutSuccessUrl("/logoutSuccess?_logout") 
            	.logoutSuccessUrl("/login?_logout") 
    			.invalidateHttpSession(true)
    			//.logoutSuccessHandler(logoutSuccessHandler)
    			.permitAll()
                .and()
            .sessionManagement()
    			.maximumSessions(1)
    				.expiredUrl("/login?_expired").sessionRegistry(sessionRegistry())
    			.and()
    			.invalidSessionUrl("/invalidSession?_invalidSession")
    			.and()
    		.rememberMe() 
    			.tokenValiditySeconds(1209600)
    			.and()
    		.anonymous()
                .authorities("ROLE_ANON")
    		.and()
    		.exceptionHandling()
    			.accessDeniedHandler(accessDeniedHandler());
    	
    }
 
}