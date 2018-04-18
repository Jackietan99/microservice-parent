package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.AccessPerm;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.BaseMybatisService;

import java.util.List;

public interface IAccessPermService extends BaseMybatisService<AccessPerm, Long> {

	List<AccessPerm> findByPermId(Long permId) throws BussinessException;
	
	List<AccessPerm> findFullAccessPerm() throws BussinessException;
	
	int batchInsert(List<AccessPerm> accessPerms) throws BussinessException;
	
	int batchDelete(Long[] ids) throws BussinessException;

	int deleteByPermId(Long permId) throws BussinessException;
	
}
