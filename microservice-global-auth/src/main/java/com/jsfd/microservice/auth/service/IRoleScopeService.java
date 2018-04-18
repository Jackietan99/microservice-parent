package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.RoleScope;
import com.lottery.core.mybatis.service.BaseMybatisService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleScopeService extends BaseMybatisService<RoleScope,Long> {
	
	List<RoleScope> findByRoleId(@Param("roleId") Long roleId);

	int batchInsert(List<RoleScope> roleScopes);

    int deleteByRoleId(@Param("roleId") Long roleId);
	
}
