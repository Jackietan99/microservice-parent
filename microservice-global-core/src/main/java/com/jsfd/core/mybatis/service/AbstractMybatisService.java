package com.jsfd.core.mybatis.service;

import com.jsfd.core.mybatis.dao.BaseMybatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractMybatisService<T, ID extends Serializable> extends SqlSessionDaoSupport implements BaseMybatisService<T, ID> {

	protected BaseMybatisDao<T, ID> baseDao;
	
	@Autowired
	public void setBaseDao(BaseMybatisDao<T, ID> baseDao) {
		this.baseDao = baseDao;
	}
	
	public BaseMybatisDao<T, ID> getBaseDao() {
		return this.baseDao;
	}
	
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	protected void handleDeleteEvent(ID id) {}
	
	@Override
	public Integer save(T entity) {
		return baseDao.insertSelective(entity);
	}

	@Override
	public Integer update(T entity) {
		/*
		if(entity instanceof AbstractBusineEntity){
			AbstractBusineEntity<ID> busineEntity = (AbstractBusineEntity<ID>) entity;
			busineEntity.setCreateDate(new Date());
		}
		*/
		return baseDao.updateByPrimaryKeySelective(entity);
	}
	
	@Override
	public T getById(ID id) {
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteById(ID id) {
		int delete = baseDao.deleteByPrimaryKey(id);
		if(delete==1){
			handleDeleteEvent(id);
		}
		return delete;
	}
	

}
