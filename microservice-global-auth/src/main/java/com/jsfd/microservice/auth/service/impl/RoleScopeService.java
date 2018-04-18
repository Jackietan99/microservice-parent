package com.jsfd.microservice.auth.service.impl;

import com.lottery.auth.domain.RoleScope;
import com.lottery.auth.mapper.RoleScopeMapper;
import com.lottery.auth.service.IRoleScopeService;
import com.lottery.core.mybatis.service.AbstractMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleScopeService extends AbstractMybatisService<RoleScope, Long> implements IRoleScopeService {
	@Autowired
	private RoleScopeMapper roleScopeMapper;
	
	@Override
	public int batchInsert(List<RoleScope> roleScopes) {
		return roleScopeMapper.batchInsert(roleScopes);
	}

	@Override
	public List<RoleScope> findByRoleId(Long roleId) {
		return roleScopeMapper.findByRoleId(roleId);
	}

	@Override
	public int deleteByRoleId(Long roleId) {
		return roleScopeMapper.deleteByRoleId(roleId);
	}

}
