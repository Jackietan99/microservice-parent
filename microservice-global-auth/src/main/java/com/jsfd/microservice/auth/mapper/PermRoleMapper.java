package com.jsfd.microservice.auth.mapper;


import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import com.jsfd.microservice.auth.pojo.PermRole;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface PermRoleMapper extends BaseMybatisDao<PermRole, Long> {
	
    List<PermRole> findByRoleId(@Param("roleId") Long roleId);

    int batchInsert(List<PermRole> accessPerms);

    int deleteByRoleId(@Param("roleId") Long roleId);
}