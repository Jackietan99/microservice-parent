package com.jsfd.core.entity;

import java.io.Serializable;

/**
 * 泛型实体类基类。
 * <p> 泛型抽象实体基类，提供统一的ID，和相关的基本功能方法
 * 如果是oracle请参考{@link GeneriBaseOracleEntity}
 */
public abstract class GeneriBaseEntity<ID extends Serializable> extends AbstractEntity<ID> {

	private static final long serialVersionUID = 0L;
	
	private ID id;
    
    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }
}
