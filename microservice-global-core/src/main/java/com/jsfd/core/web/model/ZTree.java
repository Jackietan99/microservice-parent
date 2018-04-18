package com.jsfd.core.web.model;

import java.io.Serializable;

/**
 * ztree类.
 */
public class ZTree<ID extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 节点ID. */
	private ID id;
	/** 父节点ID. */
	private ID pId;
	/** 节点名称. */
    private String name;
    /** 自定义图标. */
    private String iconSkin;
    /** 节点状态，true:展开;false:折叠. */
    private boolean open;
    /** 是否根节点，true:表示是根节点;false:表示不是根节点. */
    private boolean root;
    /** 是否父节点，true:表示是父节点;false:表示不是父节点. */
    private boolean isParent;
    
    private Object attributes;
    
    /** 是否拖拽,false禁止拖拽. */
    private boolean drag = true;
    /** 
     * 是否是否隐藏 选择框 .
     * true 表示此节点不显示 checkbox / radio，不影响勾选的关联关系，不影响父节点的半选状态;
     * false 表示节点具有正常的勾选功能
     */
    private boolean nocheck = false;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getpId() {
        return pId;
    }

    public void setpId(ID pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	public boolean getDrag() {
		return drag;
	}

	public void setDrag(boolean drag) {
		this.drag = drag;
	}
/*	
	public void addAttribute(String name,Object value) {
		LinkedHashMap<String, Object> model = null; 
		if(getAttributes() == null){
			model = new LinkedHashMap<String, Object>();
			setAttributes(model);
		}
		
		if(model instanceof LinkedHashMap){
			
		}
		
		model.put(name, value);
	
	}
	
	public static void sayClass(Object o){
		System.out.println(o.getClass().getName());
		if(o instanceof Map){
            System.out.println("is Map");
        }else if(o instanceof String){
            System.out.println("is String");
        }
    }
    
    public static void equalClass(Object o){
    	System.out.println(o.getClass().getName());
    	if(o.getClass().equals(Map.class)){
            System.out.println("is Map");
        }else if(o.getClass().equals(String.class)){
            System.out.println("is String");
        }
        
    }
*/


}
