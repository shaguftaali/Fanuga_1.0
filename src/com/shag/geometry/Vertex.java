package com.shag.geometry;

import com.shag.Math.Vector3;

public class Vertex {

public static final int SIZE=3;
	
	private Vector3 pos;
	
	public Vertex(Vector3 pos) {
		this.setPos(pos);
	}

	public Vector3 getPos() {
		return pos;
	}

	public void setPos(Vector3 pos) {
		this.pos = pos;
	}
}
