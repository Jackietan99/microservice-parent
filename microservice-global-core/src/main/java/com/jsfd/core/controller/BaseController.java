package com.jsfd.core.controller;

import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.web.model.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@Scope("prototype")
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected String viewPrefix;
	
	protected BaseController() {
        setViewPrefix(defaultViewPrefix());
    }
    /**
     * 当前模块 视图的前缀
     * 默认
     * 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     */
    private void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    private String getViewPrefix() {
        return viewPrefix;
    }
	
     /** 获取当前类头上的@RequestMapping中的value作为前缀.*/
    private String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }
        return currentViewPrefix;
    }
    
    /** 获取视图名称：即prefixViewName + "/" + suffixName.*/
    protected String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
    }
    
    /** 重定向,backURL=null时,将重定向到默认getViewPrefix().*/
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getViewPrefix();
        }
        if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
            backURL = "/" + backURL;
        }
        return "redirect:" + backURL;
    }
    /** 转向,backURL=null时,将重定向到默认getViewPrefix().*/
    protected String forwardToUrl(String backURL) {
    	if (StringUtils.isEmpty(backURL)) {
    		backURL = getViewPrefix();
    	}
    	if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
    		backURL = "/" + backURL;
    	}
    	return "forward:" + backURL;
    }
    
	/** 公共的返回成功结果方法. */
    protected AjaxResult success(Object obj) throws BussinessException {
		return new AjaxResult().success(obj);

	}
    /** 公共的返回成功结果方法. */
    protected AjaxResult success(Object obj, Map<String, Object> attributes) {
		return this.success(obj).addAttributes(attributes);
	}
	
	/** 公共的返回失败结果方法. */
    protected AjaxResult failure(String message) throws BussinessException {
		return new AjaxResult().failure(message);
	}
	
}
