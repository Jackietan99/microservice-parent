package com.jsfd.microservice.auth.service.impl;

import com.lottery.auth.domain.RoleUser;
import com.lottery.auth.mapper.RoleUserMapper;
import com.lottery.auth.service.IRoleUserService;
import com.lottery.core.mybatis.service.AbstractMybatisService;
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
