package com.dcfun.bean.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductType implements Serializable{

	private static long serialVersionUID = -6673782200702141305L;
	private Integer typeid;
	private String name;
	private String note;
	private Boolean visible = true;
	private Set<ProductType> childtypes = new HashSet<ProductType>();
	private ProductType parent;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		ProductType.serialVersionUID = serialVersionUID;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.REMOVE})
	public Set<ProductType> getChildtypes() {
		return childtypes;
	}

	public void setChildtypes(Set<ProductType> childtypes) {
		this.childtypes = childtypes;
	}

	@ManyToOne(cascade=CascadeType.REFRESH)
	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProductType other = (ProductType) obj;
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		return true;
	}

	@Column(length=40,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=200)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(nullable=false)
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	
	
}
