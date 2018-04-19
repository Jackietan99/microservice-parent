package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.core.mybatis.util.JqGridPageUtils;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.mapper.PermTypeMapper;
import com.jsfd.microservice.auth.pojo.PermType;
import com.jsfd.microservice.auth.service.IPermTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermTypeService extends AbstractMybatisService<PermType, Long> implements IPermTypeService {
	@Autowired
	private PermTypeMapper permTypeMapper;
	
	@Override
	public PageInfoWrap<PermType> findPage(Map<String, Object> parameterMap) throws BussinessException {
		Integer page = JqGridPageUtils.getPage(parameterMap);
		Integer rows = JqGridPageUtils.getRows(parameterMap);
		PageHelper.startPage(page, rows);
		List<PermType> list = permTypeMapper.findByMap(parameterMap);
		PageInfoWrap<PermType> pageInfoWrap = new PageInfoWrap<PermType>(list);
		return pageInfoWrap;
	}

	@Override
	public boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException {
		Map<String, Object> params = new HashMap<String, Object>();
        params.put(fieldId,fieldValue);
        params.put("excludeId",excludeId);
        
        int num= permTypeMapper.getCountByMap(params);
        
		return num > 0;
	}

	@Override
	public int batchDisabled(Long[] ids) throws BussinessException {
		return permTypeMapper.batchDisabled(ids);
	}

	@Override
	public int batchEnabled(Long[] ids) throws BussinessException {
		return permTypeMapper.batchEnabled(ids);
	}

	@Override
	public List<PermType> findAll() throws BussinessException {
		return permTypeMapper.findAll();
	}


}
