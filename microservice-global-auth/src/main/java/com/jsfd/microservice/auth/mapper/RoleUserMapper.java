package com.jsfd.microservice.auth.mapper;


import com.jsfd.microservice.auth.pojo.RoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleUserMapper extends BaseMybatisDao<RoleUser, Long> {
	
    List<RoleUser> findByUserId(@Param("userId") Long userId);

    int batchInsert(List<RoleUser> roleUserStatuss);

    int deleteByUserId(@Param("userId") Long userId);
}