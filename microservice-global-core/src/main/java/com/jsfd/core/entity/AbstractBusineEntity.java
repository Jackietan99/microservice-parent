package com.jsfd.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 泛型实体类泛型业务抽象类。
 */
public abstract class AbstractBusineEntity<ID extends Serializable> extends AbstractEntity<ID> {
	
	private static final long serialVersionUID = 0L;
	
	/**
	 * 实体基类的属性名称：创建日期
	 */
	public static final String CREATEDATE = "createDate";
	/**
	 * 实体基类的属性名称：修改日期
	 */
	public static final String UPDATEDATE = "updateDate";

	public abstract Date getCreateDate();
	public abstract void setCreateDate(Date createDate);
	
	public abstract Date getUpdateDate();
	public abstract void setUpdateDate(Date createDate);
}
