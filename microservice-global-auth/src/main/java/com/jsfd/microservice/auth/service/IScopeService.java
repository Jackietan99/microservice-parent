package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.Scope;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.BaseMybatisService;
import com.lottery.core.mybatis.util.PageInfoWrap;

import java.util.List;
import java.util.Map;

public interface IScopeService extends BaseMybatisService<Scope, Long> {
	
	PageInfoWrap<Scope> findPage(Map<String, Object> queryParams) throws BussinessException;

	List<Scope> findAll() throws BussinessException;
	
	List<Scope> findByMap(Map<String, Object> params) throws BussinessException;
	
	boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException;
	
	int batchDisabled(Long[] ids) throws BussinessException;
	
	int batchEnabled(Long[] ids) throws BussinessException;
	
}

