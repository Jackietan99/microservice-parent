package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.RoleUser;
import com.lottery.core.mybatis.service.BaseMybatisService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleUserService extends BaseMybatisService<RoleUser,Long> {
    
    List<RoleUser> findByUserId(@Param("userId") Long userId);

    int batchInsert(List<RoleUser> roleUsers);

    int deleteByUserId(@Param("userId") Long userId);
}
