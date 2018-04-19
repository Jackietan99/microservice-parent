package com.jsfd.microservice.auth.service.impl;


import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.microservice.auth.mapper.PermRoleMapper;
import com.jsfd.microservice.auth.pojo.PermRole;
import com.jsfd.microservice.auth.service.IPermRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermRoleService extends AbstractMybatisService<PermRole, Long> implements IPermRoleService {
	
	@Autowired
	private PermRoleMapper permRoleMapper;
	
	@Override
	public int batchInsert(List<PermRole> accessPerms) throws BussinessException {
		return permRoleMapper.batchInsert(accessPerms);
	}

	@Override
	public List<PermRole> findByRoleId(Long roleId) throws BussinessException {
		return permRoleMapper.findByRoleId(roleId);
	}

	@Override
	public int deleteByRoleId(Long roleId) throws BussinessException {
		return permRoleMapper.deleteByRoleId(roleId);
	}
	

}
