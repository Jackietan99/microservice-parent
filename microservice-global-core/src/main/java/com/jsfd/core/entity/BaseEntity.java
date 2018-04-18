package com.jsfd.core.entity;

/**
 * 实体类基类，
 */
public abstract class BaseEntity extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
