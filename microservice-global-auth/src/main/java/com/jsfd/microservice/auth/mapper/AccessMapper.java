package com.jsfd.microservice.auth.mapper;


import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import com.jsfd.microservice.auth.pojo.Access;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface AccessMapper extends BaseMybatisDao<Access, Long> {
	
	List<Access> findByPermCodes(@Param("permCodes") List<String> permCodes);

    List<Access> findByScopeAndPermCodes(Map<String, Object> params);

    List<Access> findByPermId(@Param("permId") Long permId);

	List<Access> findByMap(Map<String, Object> params);

	List<Access> findTreeByMap(Map<String, Object> params);

	List<Access> findChildsBy(@Param("parentId") Long parentId);

	List<Access> findByIds(@Param("ids") Long[] ids);

	List<Access> findAll();

	int getCountByMap(Map<String, Object> params);

	int batchDisabled(@Param("ids") Long[] ids);

	int batchEnabled(@Param("ids") Long[] ids);

	int batchDelete(@Param("ids") Long[] ids);

	int batchUpdate(List<Access> accesssList);

	int nextPriorityByParentId(@Param("parentId") Long parentId);

}