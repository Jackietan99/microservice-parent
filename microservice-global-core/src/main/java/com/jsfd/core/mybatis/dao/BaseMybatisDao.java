package com.jsfd.core.mybatis.dao;

import java.io.Serializable;

/**
 * 基于Mybatis的基础DAO接口
 * 
 * @param <T>  业务实体类型
 * @param <ID> ID类型 ，如：String、Long、Integer 等
 */
public interface BaseMybatisDao<T, ID extends Serializable> {

	/**
	 * 保存（持久化）对象
	 * int insertSelective(T entity);
	 * Integer save(T entity);
	 * @param entity  要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public Integer insertSelective(T entity);

	/**
	 * 更新（持久化）对象
	 * int updateByPrimaryKeySelective(T record);
	 * Integer update(T entity);
	 * @param entity  要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public Integer updateByPrimaryKeySelective(T entity);

	/**
	 * 获取指定的唯一标识符对应的持久化对象
	 *  T selectByPrimaryKey(ID id);
	 *  T getById(ID id);
	 * @param id  指定的唯一标识符
	 * @return 指定的唯一标识符对应的持久化对象，如果没有对应的持久化对象，则返回null。
	 */
	public T selectByPrimaryKey(ID id);

	/**
	 * 删除指定的唯一标识符对应的持久化对象
	 * int deleteByPrimaryKey(ID id);
	 * Integer deleteById(ID id);
	 * @param id 指定的唯一标识符
	 * @return 删除的对象数量
	 */
	public Integer deleteByPrimaryKey(ID id);

}
