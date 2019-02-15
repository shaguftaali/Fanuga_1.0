package com.shag.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DSDatabase extends DSBase{
	
	public static final byte[] HEADER="GSDB".getBytes();
	public static final short VERSION = 0x0001;	
	public static final byte CONTAINER_TYPE=ContainerType.DATABASE;   
	
	private short objectCount;
	public List<DSObject> objects=new ArrayList<DSObject>();

	public DSDatabase(String name) {
		size+=HEADER.length+2+1+2;
		setName(name);
	}
	
	public DSDatabase() {
		size+=HEADER.length+2+1+2;
	}

	public void addObject(DSObject object) {
		objects.add(object);
		size+=object.getSize();
		objectCount=(short)objects.size();
		
	}
	
	public int getSize() {
		
		return size;
	}
	
	public int getBytes(byte[] dest, int pointer) {
		pointer=SerializationWriter.writeBytes(dest, pointer, HEADER);
		pointer=SerializationWriter.writeBytes(dest, pointer, VERSION);
		pointer=SerializationWriter.writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer=SerializationWriter.writeBytes(dest, pointer, nameLength);
		pointer=SerializationWriter.writeBytes(dest, pointer, name);
		pointer=SerializationWriter.writeBytes(dest, pointer, size);
		
		pointer=SerializationWriter.writeBytes(dest, pointer, objectCount);
		
		for (DSObject gsObject : objects) {
			pointer=gsObject.getBytes(dest, pointer);
		}
		
		
		return pointer;
		
	}
	
	public static DSDatabase Deserialize(byte[] data) {
		int pointer=0;
		String header=SerializationWriter.readString(data, pointer, HEADER.length);
		assert(header.equals(HEADER));
		pointer+=HEADER.length;
		
		short version=SerializationWriter.readShort(data, pointer);
		if(version!=VERSION) {
			System.err.println("Invalid GSDB version");
			return null;
		}
		pointer+=2;
		
		byte containerType=SerializationWriter.readByte(data,pointer);
		assert(containerType==CONTAINER_TYPE);
		pointer++;
		
		DSDatabase result=new DSDatabase();
		
		result.nameLength= SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		result.name=SerializationWriter.readString(data, pointer, result.nameLength).getBytes();
		pointer+=result.nameLength;
		
		result.size=SerializationWriter.readInt(data, pointer);
		pointer+=4;
		
		result.objectCount=SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		
		for (int i = 0; i < result.objectCount; i++) {
			DSObject object=DSObject.Deserialize(data, pointer);
			result.objects.add(object);
			pointer+=object.getSize();
		}
		
		System.out.println(header);
		return result;
	}
	
	public DSObject findObject(String name) {
		for (DSObject object : objects) {
			if(object.getName().equals(name)) {
				return object;
			}
		}
		
		return null;
	}
	
	public static DSDatabase DeserializeFromFile(String path) {
		byte[] buffer=null;
		try {
			BufferedInputStream stream=new BufferedInputStream(new FileInputStream(path));
			buffer=new byte[stream.available()];
			stream.read(buffer);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Deserialize(buffer);
	}
	
	public void serializeToFile(String path) {
		byte[] data=new byte[getSize()];
		getBytes(data, 0);
		try {
			BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(path));
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
