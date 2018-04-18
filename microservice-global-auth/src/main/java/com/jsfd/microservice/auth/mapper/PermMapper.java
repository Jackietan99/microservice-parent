package com.jsfd.microservice.auth.mapper;


import com.jsfd.microservice.auth.pojo.Perm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermMapper extends BaseMybatisDao<Perm, Long> {
	
	List<Perm> findAll();
	
	List<Perm> findByRoleId(Long roleId);
	
	List<Perm> findByMap(Map<String, Object> params);
	
	List<Perm>  findByUserId(@Param("userId") Long userId);
	
	List<Perm> findByFuzzyName(@Param("fuzzyName") String fuzzyName);

	int getCountByMap(Map<String, Object> params);

	int batchDisabled(@Param("ids") Long[] ids);

	int batchEnabled(@Param("ids") Long[] ids);
	
	
}