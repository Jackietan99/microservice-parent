package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.core.mybatis.util.JqGridPageUtils;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.mapper.ScopeMapper;
import com.jsfd.microservice.auth.pojo.Scope;
import com.jsfd.microservice.auth.service.IScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScopeService extends AbstractMybatisService<Scope, Long> implements IScopeService {
	
	@Autowired
	private ScopeMapper scopeMapper;
	
	@Override
	public PageInfoWrap<Scope> findPage(Map<String, Object> parameterMap) throws BussinessException {
		Integer page = JqGridPageUtils.getPage(parameterMap);
		Integer rows = JqGridPageUtils.getRows(parameterMap);
		PageHelper.startPage(page, rows);
		List<Scope> list = scopeMapper.findByMap(parameterMap);
		PageInfoWrap<Scope> pageInfoWrap = new PageInfoWrap<Scope>(list);
		return pageInfoWrap;
	}

	@Override
	public boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(fieldId, fieldValue);
		params.put("excludeId", excludeId);
		int num = scopeMapper.getCountByMap(params);
		return num > 0;
	}

	@Override
	public int batchDisabled(Long[] ids) throws BussinessException {
		return scopeMapper.batchDisabled(ids);
	}

	@Override
	public int batchEnabled(Long[] ids) throws BussinessException {
		return scopeMapper.batchEnabled(ids);
	}

	@Override
	public List<Scope> findAll() throws BussinessException {
		return scopeMapper.findAll();
	}

	@Override
	public List<Scope> findByMap(Map<String, Object> params) throws BussinessException {
		return scopeMapper.findByMap(params);
	}

}
