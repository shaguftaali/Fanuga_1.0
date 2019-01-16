package com.shag.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;


import com.shag.Math.Matrix4;
import com.shag.geometry.Vertex;

public class Util {

	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size) {
		return BufferUtils.createIntBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int... values) {
		IntBuffer buffer=createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
		
		FloatBuffer buffer=createFloatBuffer(vertices.length*Vertex.SIZE);
		
		for (int i = 0; i < vertices.length; i++) {
			buffer.put(vertices[i].getPos().x);
			buffer.put(vertices[i].getPos().y);
			buffer.put(vertices[i].getPos().z);
		}
		buffer.flip();
		return buffer;
	}
	
public static FloatBuffer createFlippedBuffer(Matrix4 value) {
		
		FloatBuffer buffer=createFloatBuffer(4*4);
		
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
//				buffer.put(value.get(i,j));
				buffer.put(value.get(i)[j]);
			}
		}
		buffer.flip();
		return buffer;
	}

	public static String[] removeEmptyStrings(String[] data) {
		
		ArrayList<String> result=new ArrayList<String>();
		
		for (int i = 0; i < data.length; i++) {
			if(!data[i].equals("")) {
				result.add(data[i]);
			}
		}
		
		String[] res=new String[result.size()];
		result.toArray(res);
		
		return res;
	}
	
	public static int[] toIntArray(Integer[] data) {
		
		int[] result=new int[data.length];
		
		for (int i = 0; i < data.length; i++) {
			result[i]=data[i].intValue();
		}
		
		return result;
	}

}
