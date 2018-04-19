package com.jsfd.microservice.auth.service;



import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.BaseMybatisService;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.pojo.Scope;

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

