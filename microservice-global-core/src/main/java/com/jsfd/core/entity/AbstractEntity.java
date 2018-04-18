package com.jsfd.core.entity;

import java.io.Serializable;

/**
 * 泛型实体类。
 * 泛型抽象实体基类，如果主键是数据库端自动生成 请使用{@link BusineEntity}，如果是Oracle 请使用{@link GeneriBaseOracleEntity}
 */
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
	private static final long serialVersionUID = 0L;
	
	/**
	 * 实体基类的属性名称：实体ID
	 */
	public static final String ID = "id";
   
	public abstract ID getId();

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public abstract void setId(final ID id);

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    public boolean isNew() {

        return null == getId();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!this.getClass().getName().split("_")[0].equals( obj.getClass().getName().split("_")[0])) {
        	return false;
        }
        /*
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        */
        AbstractEntity<?> that = (AbstractEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 15;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }

    /*
	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass().getPackage() != obj.getClass().getPackage()) {
			return false;
		}
		final BusineEntity<?> other = (BusineEntity<?>) obj;
		if (id == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!id.equals(other.getId())) {
			return false;
		}
		return true;
	}
	*/
}
