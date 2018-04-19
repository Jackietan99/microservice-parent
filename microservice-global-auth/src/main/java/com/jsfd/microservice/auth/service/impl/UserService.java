package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.core.mybatis.util.JqGridPageUtils;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.mapper.RoleUserMapper;
import com.jsfd.microservice.auth.mapper.UserMapper;
import com.jsfd.microservice.auth.pojo.Role;
import com.jsfd.microservice.auth.pojo.RoleUser;
import com.jsfd.microservice.auth.pojo.User;
import com.jsfd.microservice.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService extends AbstractMybatisService<User, Long> implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleUserMapper roleUserMapper;

	@Override
	public User getUserRolesById(Long id) throws BussinessException {
		return userMapper.getUserRolesById(id);
	}

	@Override
	public User getUserRolesPermsById(Long id) throws BussinessException {
		return userMapper.getUserRolesPermsById(id);
	}

	@Override
	public User getByUsername(String username) throws BussinessException {
		User user = null;
		List<User> users = userMapper.findByUsername(username);
		if (users.size() == 1) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public List<User> findByIsEnabled(Integer isEnabled) throws BussinessException {
		return userMapper.findByIsEnabled(isEnabled);
	}

	@Override
	public int getCountByIsEnabled(Integer isEnabled) throws BussinessException {
		return userMapper.getCountByIsEnabled(isEnabled);
	}

	@Override
	public List<User> findAll() throws BussinessException {
		return userMapper.findAll();
	}

	@Override
	public List<User> findBy(Map<String, Object> params) throws BussinessException {

		return userMapper.findByMap(params);
	}

	@Override
	public int batchDisabled(Long[] ids) throws BussinessException {

		return userMapper.batchDisabled(ids);

	}

	@Override
	public int batchEnabled(Long[] ids) throws BussinessException {
		return userMapper.batchEnabled(ids);
	}

	@Override
	public int updateRoleUser(User user, Long[] roleIds) throws BussinessException {
		Integer result = 0;
		if (user.getId() != null) {
			List<RoleUser> oldRoleUserStatusList = roleUserMapper.findByUserId(user.getId());
			if (oldRoleUserStatusList.size() > 0) {
				Long[] oldRoleIds = obtainRoleIds(oldRoleUserStatusList);
				Arrays.sort(oldRoleIds);
				Arrays.sort(roleIds);
				if (!Arrays.equals(oldRoleIds,roleIds)) { 
					result = 1;
					roleUserMapper.deleteByUserId(user.getId());
				}
			}
			if (roleIds.length > 0) {
				result = 1;
				List<RoleUser> newRoleUserList = fillRoleUserStatus(user, roleIds);
				roleUserMapper.batchInsert(newRoleUserList);
			}
		}
		return result;
	}
	private List<RoleUser> fillRoleUserStatus(User user, Long[] roleIds) {
		List<RoleUser> list = new ArrayList<RoleUser>();
		int roleLength = roleIds.length;
		if (roleLength == 0) {
			return list;
		}
		if (roleLength == 1) {
			list.add(new RoleUser(user, new Role(roleIds[0])));
		} else {
			for (int i = 0; i < roleLength; i++) {
				list.add(new RoleUser(user, new Role(roleIds[i])));
			}
		}
		return list;
	}
	private Long[] obtainRoleIds(List<RoleUser> roleUserList) {
		Set<Long> ids = Sets.newHashSet(
				Lists.transform(roleUserList, 
						new Function<RoleUser, Long>() {
			@Override
			public Long apply(RoleUser roleUserStatus) {
				return roleUserStatus.getRole().getId();
			}
		}));
		return ids.toArray(new Long[ids.size()]);
	}

	@Override
	public User getByUserRefIdAndType(String userRefId, Integer type) {
		return userMapper.getByUserRefIdAndType(userRefId, type);
	}

	@Override
	public PageInfoWrap<User> findPage(Map<String, Object> parameterMap) throws BussinessException {
		Integer page = JqGridPageUtils.getPage(parameterMap);
		Integer rows = JqGridPageUtils.getRows(parameterMap);
		PageHelper.startPage(page, rows);
		List<User> list = userMapper.findByMap(parameterMap);
		PageInfoWrap<User> pageInfoWrap = new PageInfoWrap<User>(list);
		return pageInfoWrap;
	}

	@Override
	public boolean isExist(String fieldName, String fieldValue, Long excludeId) throws BussinessException {
		if(fieldName == null && "".equals(fieldName) && fieldValue == null && "".equals(fieldValue)) {
			return false;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String[] fieldNames = fieldName.split(",");
		String[] fieldValues = fieldValue.split(",");
		if(fieldNames.length == fieldValues.length) {
			for (int i = 0; i < fieldValues.length; i++) {
				params.put(fieldNames[i], fieldValues[i]);
			}
		}
		params.put("excludeId", excludeId);
		int num = userMapper.getCountByMap(params);
		return num > 0;
	}
	
}
