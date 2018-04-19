package com.jsfd.microservice.auth.mapper;



import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import com.jsfd.microservice.auth.pojo.LoginLog;

import java.util.List;
import java.util.Map;

public interface LoginLogMapper extends BaseMybatisDao<LoginLog, Long> {
	
	List<LoginLog> findByMap(Map<String, Object> params);
	
}