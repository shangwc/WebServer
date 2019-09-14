package com01;

public class Entity {

	private String name ;
	private String cls;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	@Override
	public String toString() {
		return "Entity [name=" + name + ", cls=" + cls + "]";
	}
	
	
	
}
