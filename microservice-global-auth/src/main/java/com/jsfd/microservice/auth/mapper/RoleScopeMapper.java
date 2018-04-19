package com.jsfd.microservice.auth.mapper;


import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import com.jsfd.microservice.auth.pojo.RoleScope;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleScopeMapper extends BaseMybatisDao<RoleScope, Long> {
	
    List<RoleScope> findByRoleId(@Param("roleId") Long roleId);

    int batchInsert(List<RoleScope> roleScopes);

    int deleteByRoleId(@Param("roleId") Long roleId);
    
}