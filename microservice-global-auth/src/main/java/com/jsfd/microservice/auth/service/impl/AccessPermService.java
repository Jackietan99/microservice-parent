package com.jsfd.microservice.auth.service.impl;

import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.microservice.auth.mapper.AccessPermMapper;
import com.jsfd.microservice.auth.pojo.AccessPerm;
import com.jsfd.microservice.auth.service.IAccessPermService;
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
