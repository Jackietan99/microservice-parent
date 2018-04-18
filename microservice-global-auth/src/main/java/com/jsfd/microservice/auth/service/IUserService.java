package com.jsfd.microservice.auth.service;

import com.lottery.auth.domain.User;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.BaseMybatisService;
import com.lottery.core.mybatis.util.PageInfoWrap;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserService extends BaseMybatisService<User,Long> {
	
	PageInfoWrap<User> findPage(Map<String, Object> parameterMap) throws BussinessException;
	
	/**
	 * 根据是否有效查找用户，参数isEnabled为空时查找所有.
	 */
	List<User> findByIsEnabled(Integer isEnabled) throws BussinessException;
	
	List<User> findAll() throws BussinessException;
	
	List<User> findBy(Map<String, Object> params) throws BussinessException;
	
	/**
	 * 根据用户名查找用户,用户名为唯一，非唯一时返回空.
	 */
	User getByUsername(String username) throws BussinessException;

	/**
	 * 根据用户ID查找用户、用户所包含角色.
	 */
	User getUserRolesById(Long id) throws BussinessException;
	
	/**
	 * 根据用户ID查找用户、用户所包含角色、用户所包含权限.
	 */
	User getUserRolesPermsById(Long id) throws BussinessException;

	User getByUserRefIdAndType(String userRefId, Integer type) throws BussinessException;
	
	boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException;

	/**
	 * 获取记录数，参数isEnabled为空时获取所有.
	 */
	int getCountByIsEnabled(Integer isEnabled) throws BussinessException;

	int batchDisabled(@Param("ids") Long[] ids) throws BussinessException;

	int batchEnabled(@Param("ids") Long[] ids) throws BussinessException;

	int updateRoleUser(User user, Long[] roleIds) throws BussinessException;

}
