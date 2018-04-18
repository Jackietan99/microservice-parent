package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.lottery.auth.domain.PermType;
import com.lottery.auth.mapper.PermTypeMapper;
import com.lottery.auth.service.IPermTypeService;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.AbstractMybatisService;
import com.lottery.core.mybatis.util.JqGridPageUtils;
import com.lottery.core.mybatis.util.PageInfoWrap;
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
