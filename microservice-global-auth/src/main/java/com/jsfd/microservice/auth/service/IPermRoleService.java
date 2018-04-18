package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.PermRole;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.BaseMybatisService;

import java.util.List;

public interface IPermRoleService extends BaseMybatisService<PermRole,Long> {
	
	int batchInsert(List<PermRole> accessPerms) throws BussinessException;
    
    List<PermRole> findByRoleId(Long roleId) throws BussinessException;
    
    int deleteByRoleId(Long roleId) throws BussinessException;
}
