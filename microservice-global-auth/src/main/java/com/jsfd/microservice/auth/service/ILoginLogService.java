package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.LoginLog;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.BaseMybatisService;
import com.lottery.core.mybatis.util.PageInfoWrap;

import java.util.List;
import java.util.Map;

public interface ILoginLogService extends BaseMybatisService<LoginLog,Long> {
	
	PageInfoWrap<LoginLog> findPage(Map<String, Object> parameterMap) throws BussinessException;
	
	List<LoginLog> findByMap(Map<String, Object> params) throws BussinessException;	
	
}
