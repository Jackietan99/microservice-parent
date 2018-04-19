package com.jsfd.microservice.auth.mapper;


import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import com.jsfd.microservice.auth.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMybatisDao<Role, Long> {
	
	int getCountByMap(Map<String, Object> params);
	
	int batchDisabled(@Param("ids") Long[] ids);

	int batchEnabled(@Param("ids") Long[] ids);

	List<Role> findAll();

	List<Role> findByMap(Map<String, Object> params);

	List<Role> findByFuzzyName(@Param("fuzzyName") String fuzzyName);

	List<Role> findByUserId(@Param("userId") Long userId);
}