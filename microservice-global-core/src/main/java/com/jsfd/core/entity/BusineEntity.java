package com.jsfd.core.entity;

import java.util.Date;

/**
 * 业务实体类基类.
 */
public abstract class BusineEntity extends AbstractBusineEntity<Long> {
	
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	protected Long id;
	
	/** 创建人 */
	protected String createBy;
	
	/** 创建时间 */
	protected Date createDate;
	
	/** 修改人 */
	protected String updateBy;
	
	/** 修改时间  */
	protected Date updateDate;
	
	/** 是否有效1:有效  0:无效(删除) */
	protected Boolean isEnabled;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
	
	public Boolean getIsEnabled() {
		return this.isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
