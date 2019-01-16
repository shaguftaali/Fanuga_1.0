package com.shag.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import com.shag.geometry.Vertex;



public class Mesh {

	private int vbo;
	private int ibo;
	private int size;
	
	public Mesh() {
		vbo=glGenBuffers();
		ibo=glGenBuffers();
		size=0;
	}
	
	public void addVertices(Vertex[] vertices) {
		size=vertices.length;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
	}
	
	
	public void addVertices(Vertex[] vertices,int[] indices) {
		size=indices.length*Vertex.SIZE;
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}
	
//	public void draw() {
//		glEnableVertexAttribArray(0);
//		
//		glBindBuffer(GL_ARRAY_BUFFER, vbo);
//		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE*4,0);
//		
//		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
//		glDrawElements(GL_TRIANGLES, size,GL_UNSIGNED_INT,0);
//		//glDrawArrays(GL_TRIANGLES, 0, size);
//		
//		glDisableVertexAttribArray(0);
//	}
	
	public void draw() {
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE*4,0);
		
		
		glDrawArrays(GL_TRIANGLES, 0, size);
		
		glDisableVertexAttribArray(0);
	}
}
