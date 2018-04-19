package com.jsfd.microservice.auth.mapper;


import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import com.jsfd.microservice.auth.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMybatisDao<User, Long> {
	/**
	 * 获取记录数，参数isEnabled为空时获取所有.
	 */
	int getCountByIsEnabled(@Param("isEnabled") Integer isEnabled);
	/**
	 * 根据用户名查找用户.
	 */
	List<User> findByUsername(@Param("username") String username);
	/**
	 * 根据是否有效查找用户，参数isEnabled为空时查找所有.
	 */
	List<User> findByIsEnabled(@Param("isEnabled") Integer isEnabled);
	/**
	 * 查找所有
	 */
	List<User> findAll();
	/**
	 * 根据Map参数查找
	 */
	List<User> findByMap(Map<String, Object> params);
	
	/**
	 * 根据用户ID查找用户、用户所包含角色.
	 */
	User getUserRolesById(Long id);
	/**
	 * 根据用户ID查找用户、用户所包含角色、用户所包含权限.
	 */
	User getUserRolesPermsById(Long id);

	
	int batchDisabled(@Param("ids") Long[] ids);

	int batchEnabled(@Param("ids") Long[] ids);

	User getByUserRefIdAndType(@Param("userRefId") String userRefId, @Param("type") Integer type);
	
	int getCountByMap(Map<String, Object> params);
}