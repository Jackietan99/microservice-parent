package com.jsfd.microservice.auth.service;


import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.BaseMybatisService;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.pojo.Perm;

import java.util.List;
import java.util.Map;

public interface IPermService extends BaseMybatisService<Perm,Long> {

	PageInfoWrap<Perm> findPage(Map<String, Object> params) throws BussinessException;

	List<Perm> findByFuzzyName(String term) throws BussinessException;
	
	List<Perm> findAll() throws BussinessException;

	List<Perm> findByRoleId(Long roleId) throws BussinessException;
	
	List<Perm>  findByUserId(Long userId) throws BussinessException;

	List<Perm> findByMap(Map<String, Object> params);
	
	boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException;
	
	int batchDisabled(Long[] ids) throws BussinessException;
	
	int batchEnabled(Long[] ids) throws BussinessException;

	int savePermAndAccessPerm(Perm perm, Long[] accessIds) throws BussinessException;

	int updatePermAndAccessPerm(Perm perm, Long[] accessIds) throws BussinessException;

}
