package com.shag.serialization;

public class DSBase {

	protected short nameLength;
	protected byte[] name;
	
	protected int size=2;
	
	public void setName(String name) {
		assert(name.length()<Short.MAX_VALUE);
		if(this.name!=null) {
			size-=this.nameLength;
		}
		nameLength=(short)name.length();
		this.name=name.getBytes();
		size+=nameLength;
	}
	
	public String getName() {
		return new String(name,0,nameLength);
	}
}
