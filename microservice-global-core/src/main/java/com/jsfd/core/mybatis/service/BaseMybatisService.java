package com.jsfd.core.mybatis.service;

import com.jsfd.core.exception.BussinessException;

import java.io.Serializable;

/**
 * 基于Mybatis的基础Service接口
 * 
 * @param <T>  业务实体类型
 * @param <ID> ID类型 ，如：String、Long、Integer 等
 */
public interface BaseMybatisService<T, ID extends Serializable> {

	/**
	 * 保存（持久化）对象
	 * int insertSelective(T record);
	 * 
	 * @param entity  要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public Integer save(T entity) throws BussinessException;

	/**
	 * 更新（持久化）对象
	 * int updateByPrimaryKeySelective(T record);
	 * 
	 * @param entity  要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public Integer update(T entity) throws BussinessException;;

	/**
	 * 获取指定的唯一标识符对应的持久化对象
	 *  T selectByPrimaryKey(ID id);
	 *  
	 * @param id  指定的唯一标识符
	 * @return 指定的唯一标识符对应的持久化对象，如果没有对应的持久化对象，则返回null。
	 */
	public T getById(ID id);

	/**
	 * 删除指定的唯一标识符对应的持久化对象
	 * int deleteByPrimaryKey(ID id);
	 * 
	 * @param id 指定的唯一标识符
	 * @return 删除的对象数量
	 */
	public Integer deleteById(ID id) throws BussinessException;;

}
