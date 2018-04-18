package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.Access;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.BaseMybatisService;
import com.lottery.core.mybatis.util.PageInfoWrap;

import java.util.List;
import java.util.Map;

public interface IAccessService extends BaseMybatisService<Access,Long> {
	
	PageInfoWrap<Access> findPage(Map<String, Object> parameterMap) throws BussinessException;
	
	List<Access> findByPermCodes(List<String> codes) throws BussinessException;
	
	List<Access> findByScopeAndPermCodes(Map<String, Object> params);
	
	List<Access> findByPermId(Long permId) throws BussinessException;
	
	List<Access> findByMap(Map<String, Object> params) throws BussinessException;
	
	List<Access> findTreeByMap(Map<String, Object> params) throws BussinessException;
	
	List<Access> findAll() throws BussinessException;
	
	List<Access> findChildsBy(Long parentId) throws BussinessException;
	
	boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException;
	
	int batchDisabled(Long[] ids) throws BussinessException;
	
	int batchEnabled(Long[] ids) throws BussinessException;
	
	
	int batchDelete(Long[] ids) throws BussinessException;
	/**
     * 移动节点
     * 根节点不能移动
     * @param sourceId   源节点ID
     * @param targetId   目标节点ID
     * @param moveType 位置
     */
	Access move(Long sourceId, Long targetId, String moveType) throws BussinessException;
	
	int nextPriorityBy(Long parentId) throws BussinessException;

}
