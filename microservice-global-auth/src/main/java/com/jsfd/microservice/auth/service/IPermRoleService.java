package com.jsfd.microservice.auth.service;



import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.BaseMybatisService;
import com.jsfd.microservice.auth.pojo.PermRole;

import java.util.List;

public interface IPermRoleService extends BaseMybatisService<PermRole,Long> {
	
	int batchInsert(List<PermRole> accessPerms) throws BussinessException;
    
    List<PermRole> findByRoleId(Long roleId) throws BussinessException;
    
    int deleteByRoleId(Long roleId) throws BussinessException;
}
