package com.jsfd.microservice.auth.service.impl;

import com.lottery.auth.domain.AccessPerm;
import com.lottery.auth.mapper.AccessPermMapper;
import com.lottery.auth.service.IAccessPermService;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.AbstractMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessPermService extends AbstractMybatisService<AccessPerm, Long> implements IAccessPermService {
	
	@Autowired
	private AccessPermMapper accessPermMapper;

	@Override
	public int batchInsert(List<AccessPerm> accessPerms) throws BussinessException {
		return accessPermMapper.batchInsert(accessPerms);
	}

	@Override
	public int batchDelete(Long[] ids) throws BussinessException {
		return accessPermMapper.batchDelete(ids);
	}

	@Override
	public List<AccessPerm> findByPermId(Long permId) throws BussinessException {
		return accessPermMapper.findByPermId(permId);
	}

	@Override
	public int deleteByPermId(Long permId) throws BussinessException {
		return accessPermMapper.deleteByPermId(permId);
	}

	@Override
	public List<AccessPerm> findFullAccessPerm() throws BussinessException {
		return accessPermMapper.findFullAccessPerm();
	}
	

}
