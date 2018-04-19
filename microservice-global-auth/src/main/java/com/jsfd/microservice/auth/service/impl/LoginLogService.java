package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.core.mybatis.util.JqGridPageUtils;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.mapper.LoginLogMapper;
import com.jsfd.microservice.auth.pojo.LoginLog;
import com.jsfd.microservice.auth.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginLogService extends AbstractMybatisService<LoginLog, Long> implements ILoginLogService {
	
	@Autowired
	private LoginLogMapper loginLogMapper;
	
	@Override
	public PageInfoWrap<LoginLog> findPage(Map<String, Object> parameterMap) throws BussinessException {
		Integer page = JqGridPageUtils.getPage(parameterMap);
		Integer rows = JqGridPageUtils.getRows(parameterMap);
		PageHelper.startPage(page, rows);
		List<LoginLog> list = loginLogMapper.findByMap(parameterMap);
		PageInfoWrap<LoginLog> pageInfoWrap = new PageInfoWrap<LoginLog>(list);
		return pageInfoWrap;
	}

	@Override
	public List<LoginLog> findByMap(Map<String, Object> params) throws BussinessException {
		return loginLogMapper.findByMap(params);
	}

}
