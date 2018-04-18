package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lottery.auth.domain.Access;
import com.lottery.auth.domain.AccessPerm;
import com.lottery.auth.domain.Perm;
import com.lottery.auth.mapper.PermMapper;
import com.lottery.auth.service.IAccessPermService;
import com.lottery.auth.service.IPermService;
import com.lottery.core.exception.BussinessException;
import com.lottery.core.mybatis.service.AbstractMybatisService;
import com.lottery.core.mybatis.util.JqGridPageUtils;
import com.lottery.core.mybatis.util.PageInfoWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermService extends AbstractMybatisService<Perm, Long> implements IPermService {
	@Autowired
	private IAccessPermService accessPermService;

	@Autowired
	private PermMapper permMapper;
	
	@Override
	public List<Perm> findByMap(Map<String, Object> params) {
		return permMapper.findByMap(params);
	}

	@Override
	public PageInfoWrap<Perm> findPage(Map<String, Object> params) throws BussinessException {
		Integer page = JqGridPageUtils.getPage(params);
		Integer rows = JqGridPageUtils.getRows(params);
		PageHelper.startPage(page, rows);
		List<Perm> list = permMapper.findByMap(params);
		PageInfoWrap<Perm> pageInfoWrap = new PageInfoWrap<Perm>(list);
		return pageInfoWrap;
	}

	@Override
	public boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(fieldId, fieldValue);
		params.put("excludeId", excludeId);

		int num = permMapper.getCountByMap(params);

		return num > 0;
	}

	@Override
	public int batchDisabled(Long[] ids) throws BussinessException {

		return permMapper.batchDisabled(ids);

	}

	@Override
	public int batchEnabled(Long[] ids) throws BussinessException {
		return permMapper.batchEnabled(ids);
	}

	@Override
	public int savePermAndAccessPerm(Perm perm, Long[] accessIds) throws BussinessException {
		Integer result = save(perm);
		if (result > 0 && accessIds.length > 0) {
			List<AccessPerm> accessPermList = fillAccessPerm(perm, accessIds);
			if (accessPermList.size() > 0) {
				accessPermService.batchInsert(accessPermList);
			}
		}
		return result;
	}

	@Override
	public int updatePermAndAccessPerm(Perm perm, Long[] accessIds) throws BussinessException {
		Integer result = update(perm);
		if (result > 0 && perm.getId() != null) {
			List<AccessPerm> oldAccessPermList = accessPermService.findByPermId(perm.getId());
			if (oldAccessPermList.size() > 0) {
				Long[] oldAccessIds = obtainAccessIds(oldAccessPermList);
				Arrays.sort(oldAccessIds);
				Arrays.sort(accessIds);
				if (!Arrays.equals(oldAccessIds, accessIds)) {
					// Long[] oldAccessPermIds = obtainAccessPermIds(oldAccessPermList);
					// accessPermService.batchDelete(oldAccessPermIds);
					accessPermService.deleteByPermId(perm.getId());
				}
			}
			if (accessIds.length > 0) {
				List<AccessPerm> newAccessPermList = fillAccessPerm(perm, accessIds);
				accessPermService.batchInsert(newAccessPermList);
			}
		}

		return result;
	}

	private Long[] obtainAccessIds(List<AccessPerm> accessPermList) throws BussinessException {
		Set<Long> ids = Sets.newHashSet(Lists.transform(accessPermList, new Function<AccessPerm, Long>() {
			@Override
			public Long apply(AccessPerm accessPerm) {
				return accessPerm.getAccess().getId();
			}
		}));
		return ids.toArray(new Long[ids.size()]);
	}

	private List<AccessPerm> fillAccessPerm(Perm perm, Long[] accessIds) throws BussinessException {
		List<AccessPerm> list = new ArrayList<AccessPerm>();
		int accessLength = accessIds.length;
		if (accessLength == 0) {
			return list;
		}
		if (accessLength == 1) {
			list.add(new AccessPerm(perm, new Access(accessIds[0])));
		} else {
			for (int i = 0; i < accessLength; i++) {
				list.add(new AccessPerm(perm, new Access(accessIds[i])));
			}
		}
		return list;
	}

	@Override
	public List<Perm> findByFuzzyName(String fuzzyName) throws BussinessException {
		return permMapper.findByFuzzyName(fuzzyName);
	}

	@Override
	public List<Perm> findAll() {
		return permMapper.findAll();
	}

	@Override
	public List<Perm> findByRoleId(Long roleId) {
		return permMapper.findByRoleId(roleId);
	}

	@Override
	public List<Perm> findByUserId(Long userId) {
		return permMapper.findByUserId(userId);
	}

}
