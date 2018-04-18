package com.jsfd.microservice.auth.mapper;


import com.jsfd.microservice.auth.pojo.PermType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermTypeMapper extends BaseMybatisDao<PermType, Long> {
	
	List<PermType> findAll();
	
	List<PermType> findByMap(Map<String, Object> params);
	
	int getCountByMap(Map<String, Object> params);
	
	int batchDisabled(@Param("ids") Long[] ids);

	int batchEnabled(@Param("ids") Long[] ids);
	
	
}