package com.jsfd.core.entity;

import java.io.Serializable;

/**
 * <p> 泛型抽象实体基类，提供统一的ID，和相关的基本功能方法
 * <p> 如果是如mysql这种自动生成主键的，请参考{@link GeneriBaseEntity}
 * <p/>
 * 子类只需要在类头上加 @SequenceGenerator(name="seq", sequenceName="你的sequence名字")
 * <p/>
 */
//@MappedSuperclass
public abstract class GeneriBaseOracleEntity<PK extends Serializable> extends AbstractEntity<PK> {
	private static final long serialVersionUID = 0L;
    
    private PK id;
    
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }
}
