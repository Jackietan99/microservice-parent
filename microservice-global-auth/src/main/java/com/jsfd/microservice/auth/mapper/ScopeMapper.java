package com.jsfd.microservice.auth.mapper;


import com.jsfd.microservice.auth.pojo.Scope;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScopeMapper extends BaseMybatisDao<Scope, Long> {
	
	List<Scope> findAll();
	
	List<Scope> findByMap(Map<String, Object> params);
	
	int getCountByMap(Map<String, Object> params);
	
	int batchDisabled(@Param("ids") Long[] ids);

	int batchEnabled(@Param("ids") Long[] ids);
	
}