package com.jsfd.microservice.auth.service;



import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.BaseMybatisService;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.pojo.LoginLog;

import java.util.List;
import java.util.Map;

public interface ILoginLogService extends BaseMybatisService<LoginLog,Long> {
	
	PageInfoWrap<LoginLog> findPage(Map<String, Object> parameterMap) throws BussinessException;
	
	List<LoginLog> findByMap(Map<String, Object> params) throws BussinessException;	
	
}
