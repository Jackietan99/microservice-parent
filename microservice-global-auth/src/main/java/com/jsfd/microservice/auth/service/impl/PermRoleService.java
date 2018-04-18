package com.jsfd.microservice.auth.service.impl;

import com.lottery.auth.domain.PermRole;
import com.lottery.auth.mapper.PermRoleMapper;
import com.lottery.auth.service.IPermRoleService;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.AbstractMybatisService;
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
