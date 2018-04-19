package com.jsfd.microservice.auth.mapper;


import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import com.jsfd.microservice.auth.pojo.AccessPerm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccessPermMapper extends BaseMybatisDao<AccessPerm, Long> {
    
	List<AccessPerm> findFullAccessPerm();

    List<AccessPerm> findByPermId(@Param("permId") Long permId);

    int batchInsert(List<AccessPerm> accessPerms);

    int deleteByPermId(@Param("permId") Long permId);

    int batchDelete(@Param("ids") Long[] ids);
}