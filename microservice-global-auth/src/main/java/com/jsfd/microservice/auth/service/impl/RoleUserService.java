package com.jsfd.microservice.auth.service.impl;


import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.microservice.auth.mapper.RoleUserMapper;
import com.jsfd.microservice.auth.pojo.RoleUser;
import com.jsfd.microservice.auth.service.IRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserService extends AbstractMybatisService<RoleUser, Long> implements IRoleUserService {
	
	@Autowired
	private RoleUserMapper roleUserMapper;
	
	@Override
	public List<RoleUser> findByUserId(Long userId) {
		return roleUserMapper.findByUserId(userId);
	}

	@Override
	public int batchInsert(List<RoleUser> roleUserStatuss) {
		return roleUserMapper.batchInsert(roleUserStatuss);
	}

	@Override
	public int deleteByUserId(Long userId) {
		return roleUserMapper.deleteByUserId(userId);
	}
}
