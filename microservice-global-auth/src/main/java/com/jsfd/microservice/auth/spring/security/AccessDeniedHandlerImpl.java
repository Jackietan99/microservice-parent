package com.jsfd.microservice.auth.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @seeorg.springframework.security.web.access.AccessDeniedHandlerImpl
 * @ClassName:AccessDeniedHandlerImpl
 * @Description: TODO()
 * @author:"czlxming".
 * @CreateDate:2017年9月27日-下午10:35:46.
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private String errorPage = "/login";
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		boolean isAjax = this.isAjaxRequest(request);
		//处理Ajax请求
		if (isAjax) {
			String msg = accessDeniedException.getMessage();
			this.renderText(response, msg);
		} else if (!response.isCommitted()) {
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
		}
	}

	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}
		this.errorPage = errorPage;
	}
	/**
	 * 判断request是否Ajax请求(异步)
	 * @return
	 */
	public boolean isAjaxRequest(HttpServletRequest request) {
		//在服务器端判断request来自Ajax请求(异步)还是传统请求(同步),Ajax 请求多了个 x-requested-with 
		String requested = request.getHeader("x-requested-with");
		return requested != null && "XMLHttpRequest".equals(requested);
	}
	
	public void renderText(HttpServletResponse response, String text) {
		this.render(response, "text/plain;charset=UTF-8", text);
	}

	/**
	 * 设置HttpServletResponse响应内容数据编码为UTF-8, 发送内容。使用UTF-8编码。
	 * @param response HttpServletResponse
	 * @param contentType  Servlet contentType 响应内容数据类型
	 * @param text 发送的字符串
	 */
	public void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
