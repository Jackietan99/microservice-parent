package com.jsfd.microservice.auth.service;

import com.jsfd.core.mybatis.service.BaseMybatisService;
import com.jsfd.microservice.auth.pojo.RoleUser;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleUserService extends BaseMybatisService<RoleUser,Long> {
    
    List<RoleUser> findByUserId(@Param("userId") Long userId);

    int batchInsert(List<RoleUser> roleUsers);

    int deleteByUserId(@Param("userId") Long userId);
}
