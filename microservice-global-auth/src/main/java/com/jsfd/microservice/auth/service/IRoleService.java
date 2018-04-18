package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.Role;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.BaseMybatisService;
import com.lottery.core.mybatis.util.PageInfoWrap;

import java.util.List;
import java.util.Map;

public interface IRoleService extends BaseMybatisService<Role,Long> {
	
	PageInfoWrap<Role> findPage(Map<String, Object> parameterMap) throws BussinessException;
	
	List<Role> findByMap(Map<String, Object> params) throws BussinessException;
	
	List<Role> findAll() throws BussinessException;
	
	List<Role> findByUserId(Long userId) throws BussinessException;
	
	List<Role> findByFuzzyName(String fuzzyName) throws BussinessException;
	
	boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException;
	
	int batchDisabled(Long[] ids) throws BussinessException;
	
	int batchEnabled(Long[] ids) throws BussinessException;

	int updateRolePerm(Role role, Long[] permIds) throws BussinessException;
		
	int saveRoleAndScope(Role role, Long[] scopeIds) throws BussinessException;

	int updateRoleAndScope(Role role, Long[] scopeIds) throws BussinessException;
    
    
    
	
}
