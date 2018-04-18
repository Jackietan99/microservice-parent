package com.jsfd.core.entity.able;

import java.io.Serializable;

/**
 * 实体实现该接口表示想要实现树结构
 */
public interface Treeable<ID extends Serializable> {
	
	/** 节点名. */
	public String getName();

    /** 层级---方便无限级树操作 . */
    public Integer getLevel();

    /** 显示的图标 . */
    public String getIconCls();

    /** 父路径 . */
    public ID getParentId();
    
    /** 森林中树根名 . */
    public String getTreeRootName();
    
    /** 是否是根节点 . */
    public boolean isRoot();

    /** 是否是叶子节点 . */
    public boolean isLeaf();

}
