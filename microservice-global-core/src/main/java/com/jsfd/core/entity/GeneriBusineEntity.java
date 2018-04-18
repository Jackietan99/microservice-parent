package com.jsfd.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 泛型抽象业务实体基类，提供统一的ID、创建时间、修改时间，和相关的基本功能方法
 * 如果是oracle请参考{@link GeneriBaseOracleEntity}
 */
//@MappedSuperclass
public abstract class GeneriBusineEntity<ID extends Serializable> extends AbstractBusineEntity<ID> {
	
	private static final long serialVersionUID = 0L;
	
	/** 主键 */
	protected ID id;
	
	/** 创建人 */
	protected String createBy;
	
	/** 创建时间 */
	protected Date createDate;
	
	/** 修改人 */
	protected String updateBy;
	
	/** 修改时间  */
	protected Date updateDate;
	
	/** 是否有效1:有效  0:无效(删除) */
	protected Integer isEnabled;
	
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

	public String getCreateBy() {
		return this.createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getUpdateBy() {
		return this.updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Integer getIsEnabled() {
		return this.isEnabled;
	}
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
