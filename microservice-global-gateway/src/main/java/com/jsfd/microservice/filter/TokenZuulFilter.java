package com.jsfd.microservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class TokenZuulFilter extends ZuulFilter {
	private final Logger logger = LoggerFactory.getLogger(TokenZuulFilter.class);

	/** 验证参数是否含有Token,filter需要执行的具体操作. */
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		logger.debug("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());
		// 获取请求的参数
		String token = request.getParameter("token");
		if (StringUtils.isNotBlank(token)) {
			// 请求中含有Token,对请求进行路由
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);
			ctx.set("isSuccess", true);
			return null;
		} else {
			// 请求中没有Token,不对其进行路由
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(400);
			ctx.setResponseBody("token is empty");
			ctx.set("isSuccess", false);
			return null;
		}
	}

	/** 是否执行该过滤器，此处为true，说明需要过滤. */
	@Override
	public boolean shouldFilter() {
		// SendResponseFilter
		return true;
	}

	/** filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低. */
	@Override
	public int filterOrder() {
		return 10;
	}

	/** 在请求被路由之前调用. */
	@Override
	public String filterType() {
		return "pre";
	}
}