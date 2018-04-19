package com.jsfd.microservice.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsfd.core.exception.BussinessException;
import com.jsfd.core.mybatis.service.AbstractMybatisService;
import com.jsfd.core.mybatis.util.JqGridPageUtils;
import com.jsfd.core.mybatis.util.PageInfoWrap;
import com.jsfd.microservice.auth.mapper.AccessMapper;
import com.jsfd.microservice.auth.pojo.Access;
import com.jsfd.microservice.auth.service.IAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessService extends AbstractMybatisService<Access, Long> implements IAccessService {
	
	@Autowired
	private AccessMapper accessMapper;

	@Override
	public List<Access> findByPermCodes(List<String> codes) throws BussinessException {
		return accessMapper.findByPermCodes(codes);
	}
	
	@Override
	public List<Access> findByScopeAndPermCodes(Map<String, Object> params) {
		return accessMapper.findByScopeAndPermCodes(params);
	}

	@Override
	public List<Access> findByPermId(Long permId) throws BussinessException {
		return accessMapper.findByPermId(permId);
	}

	@Override
	public List<Access> findByMap(Map<String, Object> params) throws BussinessException {
		return accessMapper.findByMap(params);
	}

	@Override
	public List<Access> findTreeByMap(Map<String, Object> params) throws BussinessException {
		return accessMapper.findTreeByMap(params);
	}

	@Override
	public boolean isExist(String fieldId, String fieldValue, Long excludeId) throws BussinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(fieldId, fieldValue);
		params.put("excludeId", excludeId);
		int num = accessMapper.getCountByMap(params);
		return num > 0;
	}

	@Override
	public int batchDisabled(Long[] ids) throws BussinessException {
		return accessMapper.batchDisabled(ids);
	}

	@Override
	public int batchEnabled(Long[] ids) throws BussinessException {
		return accessMapper.batchEnabled(ids);
	}

	@Override
	public List<Access> findAll() {
		return accessMapper.findAll();
	}

	@Override
	public PageInfoWrap<Access> findPage(Map<String, Object> parameterMap) throws BussinessException {
		Integer page = JqGridPageUtils.getPage(parameterMap);
		Integer rows = JqGridPageUtils.getRows(parameterMap);
		PageHelper.startPage(page, rows);
		List<Access> list = accessMapper.findByMap(parameterMap);
		PageInfoWrap<Access> pageInfoWrap = new PageInfoWrap<Access>(list);
		return pageInfoWrap;
	}

	@Override
	public List<Access> findChildsBy(Long parentId) throws BussinessException {
		return accessMapper.findChildsBy(parentId);
	}

	@Override
	public Integer deleteById(Long id) throws BussinessException {
		Access access = accessMapper.selectByPrimaryKey(id);
		Access parent = access.getParent();
		List<Access> childAccessList = accessMapper.findChildsBy(access.getId());

		for (Access child : childAccessList) {
			child.setParent(parent);
			child.setLevel(child.getLevel() - 1);
		}

		if (childAccessList.size() > 0) {
			accessMapper.batchUpdate(childAccessList);
		}
		return super.deleteById(id);
	}

	@Override
	public int batchDelete(Long[] ids) throws BussinessException {
		List<Access> batchList = accessMapper.findByIds(ids);
		List<Access> updetaChilds = new ArrayList<>();
		for (Access batchAccess : batchList) {
			Access access = accessMapper.selectByPrimaryKey(batchAccess.getId());
			Access parent = access.getParent();
			List<Access> childAccessList = accessMapper.findChildsBy(access.getId());
			for (Access child : childAccessList) {
				child.setParent(parent);
				child.setLevel(child.getLevel() - 1);
			}
			updetaChilds.addAll(childAccessList);
		}
		if (updetaChilds.size() > 0) {
			accessMapper.batchUpdate(updetaChilds);
		}
		return accessMapper.batchDelete(ids);
	}

	@Override
	public Access move(Long sourceId, Long targetId, String moveType) throws BussinessException {
		if (sourceId == null || targetId == null || moveType == null) { // 根节点不能移动
			return null;
		}
		Access source = accessMapper.selectByPrimaryKey(sourceId);
		Access target = accessMapper.selectByPrimaryKey(targetId);
		if (source == null || target == null || source.isRoot()) { // 根节点不能移动
			return source;
		}
		if ("inner".equals(moveType)) {
			source.setParent(target);
			accessMapper.updateByPrimaryKeySelective(source);
			return source;
		}

		// 如果是相邻的兄弟 直接交换排序即可inner
		boolean isSibling = source.getParentId().equals(target.getParentId());
		boolean isNextOrPrevMoveType = "next".equals(moveType) || "prev".equals(moveType);
		if (isSibling && isNextOrPrevMoveType && Math.abs(source.getPriority() - target.getPriority()) == 1) {
			// 无需移动
			if ("next".equals(moveType) && source.getPriority() > target.getPriority()) {
				return source;
			}
			if ("prev".equals(moveType) && source.getPriority() < target.getPriority()) {
				return source;
			}
			int sourcePriority = source.getPriority();
			source.setPriority(target.getPriority());
			target.setPriority(sourcePriority);
			accessMapper.updateByPrimaryKeySelective(source);
			return source;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		// 移动到目标节点之后
		if ("next".equals(moveType)) {
			params.put("parentId", target.getParentId());
			params.put("gtPriority", target.getPriority());
			// 查找目标节点及之后的兄弟 注意：值越小 越排在前边
			List<Access> siblings = accessMapper.findByMap(params);
			siblings.remove(0);// 把自己移除
			if (siblings.size() == 0) { // 如果没有兄弟了 则直接把源的设置为目标即可
				int nextWeight = nextPriorityBy(target.getParentId());
				source.setPriority(nextWeight);
				accessMapper.updateByPrimaryKeySelective(source);
				return source;
			} else {
				moveType = "prev";
				target = siblings.get(0); // 否则，相当于插入到实际目标节点下一个节点之前
			}
		}

		// 移动到目标节点之前
		if ("prev".equals(moveType)) {
			params.put("parentId", target.getParentId());
			params.put("gtPriority", target.getPriority());
			// 查找目标节点及之后的兄弟 注意：值越小 越排在前边
			List<Access> siblings = accessMapper.findByMap(params);
			// 兄弟节点中包含源节点
			if (siblings.contains(source)) {
				// 1 2 [3 source] 4
				siblings = siblings.subList(0, siblings.indexOf(source) + 1);
				int firstWeight = siblings.get(0).getPriority();
				for (int i = 0; i < siblings.size() - 1; i++) {
					siblings.get(i).setPriority(siblings.get(i + 1).getPriority());
					accessMapper.updateByPrimaryKeySelective(siblings.get(i));
				}
				siblings.get(siblings.size() - 1).setPriority(firstWeight);
				accessMapper.updateByPrimaryKeySelective(siblings.get(siblings.size() - 1));
			} else {
				// 1 2 3 4 [5 new]
				int nextWeight = nextPriorityBy(target.getParentId());
				int firstWeight = siblings.get(0).getPriority();
				for (int i = 0; i < siblings.size() - 1; i++) {
					siblings.get(i).setPriority(siblings.get(i + 1).getPriority());
					accessMapper.updateByPrimaryKeySelective(siblings.get(i));
				}
				siblings.get(siblings.size() - 1).setPriority(nextWeight);
				accessMapper.updateByPrimaryKeySelective(siblings.get(siblings.size() - 1));
				source.setPriority(firstWeight);
				accessMapper.updateByPrimaryKeySelective(source);
			}
			return source;
		}
		// 否则作为最后孩子节点
		int nextWeight = nextPriorityBy(target.getId());
		source.setPriority(nextWeight);
		accessMapper.updateByPrimaryKeySelective(source);

		return source;
	}

	public int nextPriorityBy(Long id) throws BussinessException {
		// "select case when max(priority) is null then 1 else (max(priority) + 1) end
		// from SYS_AUTH_ACCESS where parentId = ?1"";
		return accessMapper.nextPriorityByParentId(id);
	}
	

}
