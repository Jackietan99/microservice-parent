package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.core.mybatis.util.JqGridPageUtils;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.mapper.PermRoleMapper;
import com.jsfd.microservice.auth.mapper.RoleMapper;
import com.jsfd.microservice.auth.mapper.RoleScopeMapper;
import com.jsfd.microservice.auth.pojo.*;
import com.jsfd.microservice.auth.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RoleService extends AbstractMybatisService<Role, Long> implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private PermRoleMapper permRoleMapper;
	
	@Autowired
	private RoleScopeMapper roleScopeMapper;
	
	@Override
	public PageInfoWrap<Role> findPage(Map<String, Object> parameterMap) throws BussinessException {
		Integer page = JqGridPageUtils.getPage(parameterMap);
		Integer rows = JqGridPageUtils.getRows(parameterMap);
		PageHelper.startPage(page, rows);
		List<Role> list = roleMapper.findByMap(parameterMap);
		PageInfoWrap<Role> pageInfoWrap = new PageInfoWrap<Role>(list);
		return pageInfoWrap;
	}

	@Override
	public List<Role> findByMap(Map<String, Object> params) throws BussinessException {
		return roleMapper.findByMap(params);
	}

	@Override
	public boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(fieldId, fieldValue);
		params.put("excludeId", excludeId);

		int num = roleMapper.getCountByMap(params);

		return num > 0;
	}

	@Override
	public int batchDisabled(Long[] ids) throws BussinessException {
		return roleMapper.batchDisabled(ids);
	}

	@Override
	public int batchEnabled(Long[] ids) throws BussinessException {
		return roleMapper.batchEnabled(ids);
	}

	@Override
	public List<Role> findAll() throws BussinessException {
		return roleMapper.findAll();
	}

	@Override
	public List<Role> findByFuzzyName(String fuzzyName) throws BussinessException {
		return roleMapper.findByFuzzyName(fuzzyName);
	}

	@Override
	public int updateRolePerm(Role role, Long[] permIds) throws BussinessException {
		Integer result = 0;
		if (role.getId() != null) {
			List<PermRole> oldPermRoleList = permRoleMapper.findByRoleId(role.getId());
			if (oldPermRoleList.size() > 0) {
				Long[] oldPermIds = obtainPermIds(oldPermRoleList);
				Arrays.sort(oldPermIds);
				Arrays.sort(permIds);
				if (!Arrays.equals(oldPermIds, permIds)) {
					result = 1;
					permRoleMapper.deleteByRoleId(role.getId());
				}
			}

			if (permIds.length > 0) {
				result = 1;
				List<PermRole> newPermRoleList = fillPermRole(role, permIds);
				permRoleMapper.batchInsert(newPermRoleList);
			}
		}
		return result;
	}

	@Override
	public List<Role> findByUserId(Long userId) throws BussinessException {
		return roleMapper.findByUserId(userId);
	}

	private List<PermRole> fillPermRole(Role role, Long[] permIds) throws BussinessException {
		List<PermRole> list = new ArrayList<PermRole>();
		int permLength = permIds.length;
		if (permLength == 0) {
			return list;
		}
		if (permLength == 1) {
			list.add(new PermRole(role, new Perm(permIds[0])));
		} else {
			for (int i = 0; i < permLength; i++) {
				list.add(new PermRole(role, new Perm(permIds[i])));
			}
		}
		return list;
	}

	private Long[] obtainPermIds(List<PermRole> permRoleList) throws BussinessException {
		Set<Long> ids = Sets.newHashSet(Lists.transform(permRoleList, new Function<PermRole, Long>() {
			@Override
			public Long apply(PermRole permRole) {
				return permRole.getPerm().getId();
			}
		}));
		return ids.toArray(new Long[ids.size()]);
	}

	@Override
	public int saveRoleAndScope(Role role, Long[] scopeIds) throws BussinessException {
		Integer result = save(role);
		if (result > 0 && scopeIds.length > 0) {
			List<RoleScope> roleScopeList = fillRoleScope(role, scopeIds);
			if (roleScopeList.size() > 0) {
				roleScopeMapper.batchInsert(roleScopeList);
			}
		}
		return result;
	}
	@Override
	public int updateRoleAndScope(Role role, Long[] scopeIds) throws BussinessException {
		Integer result = update(role);
		if (result > 0 && role.getId() != null) {
			List<RoleScope> oldRoleScopeList = roleScopeMapper.findByRoleId(role.getId());
			if (oldRoleScopeList.size() > 0) {
				Long[] oldScopeIds = obtainScopeIds(oldRoleScopeList);
				Arrays.sort(oldScopeIds);
				Arrays.sort(scopeIds);
				if (!Arrays.equals(oldScopeIds, scopeIds)) {
					// Long[] oldAccessPermIds = obtainAccessPermIds(oldAccessPermList);
					// accessPermService.batchDelete(oldAccessPermIds);
					roleScopeMapper.deleteByRoleId(role.getId());
				}
			}
			if (scopeIds.length > 0) {
				List<RoleScope> newRoleScopeList = fillRoleScope(role, scopeIds);
				roleScopeMapper.batchInsert(newRoleScopeList);
			}
		}

		return result;
	}
	private Long[] obtainScopeIds(List<RoleScope> roleScopeList) throws BussinessException {
		Set<Long> ids = Sets.newHashSet(Lists.transform(roleScopeList, new Function<RoleScope, Long>() {
			@Override
			public Long apply(RoleScope roleScope) {
				return roleScope.getScope().getId();
			}
		}));
		return ids.toArray(new Long[ids.size()]);
	}
	
	private List<RoleScope> fillRoleScope(Role role, Long[] scopeIds) throws BussinessException {
		List<RoleScope> list = new ArrayList<RoleScope>();
		int scopeLength = scopeIds.length;
		if (scopeLength == 0) {
			return list;
		}
		if (scopeLength == 1) {
			list.add(new RoleScope(role, new Scope(scopeIds[0])));
		} else {
			for (int i = 0; i < scopeLength; i++) {
				list.add(new RoleScope(role, new Scope(scopeIds[i])));
			}
		}
		return list;
	}
}
