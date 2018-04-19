package com.jsfd.microservice.auth.service;



import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.BaseMybatisService;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.pojo.PermType;

import java.util.List;
import java.util.Map;

public interface IPermTypeService extends BaseMybatisService<PermType,Long> {
	
	PageInfoWrap<PermType> findPage(Map<String, Object> params) throws BussinessException;
	
	List<PermType> findAll() throws BussinessException
			;
	
	boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException;
	
	int batchDisabled(Long[] ids) throws BussinessException;
	
	int batchEnabled(Long[] ids) throws BussinessException;
	
}
