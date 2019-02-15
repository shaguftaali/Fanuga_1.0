package com.shag.serialization;

import java.util.ArrayList;
import java.util.List;

public class DSObject  extends DSBase{

	public static final byte CONTAINER_TYPE=ContainerType.OBJECT; 
	
	private short fieldCount;
	public List<DSField> fields=new ArrayList<DSField>();
	
	private short stringCount;
	public List<DSString> strings=new ArrayList<DSString>();
	
	private short arrayCount;
	public List<DSArray> arrays=new ArrayList<DSArray>();
	
	public DSObject(String name) {
		size+=1+2+2+2;
	}
	
	public DSObject() {
		size+=1+2+2+2;	
	}
	
	public String getName() {
		return new String(name,0, nameLength);
	}
	
	public void addArray(DSArray array) {
		arrays.add(array);
		size+=array.getSize();
		arrayCount=(short)arrays.size();
	}

	public void addField(DSField field) {
		fields.add(field);
		size+=field.getSize();
		fieldCount=(short)fields.size();
		
	}
	
	public void addString(DSString string) {
		strings.add(string);
		size+=string.getSize();
		stringCount=(short)strings.size();
		
	}
	
	public int getSize() {
			
		return size;
	}
	
	public DSField findField(String name) {
		for (DSField field : fields) {
			if(field.getName().equals(name)) {
				return field;
			}
		}
		
		return null;
	}
	
	public DSString findString(String name) {
		for (DSString string : strings) {
			if(string.getName().equals(name)) {
				return string;
			}
		}
		
		return null;
	}
	
	public DSArray findArray(String name) {
		for (DSArray array : arrays) {
			if(array.getName().equals(name)) {
				return array;
			}
		}
		
		return null;
	}
	
	public int getBytes(byte[] dest, int pointer) {
		pointer=SerializationWriter.writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer=SerializationWriter.writeBytes(dest, pointer, nameLength);
		pointer=SerializationWriter.writeBytes(dest, pointer, name);
		pointer=SerializationWriter.writeBytes(dest, pointer, size);
		
		pointer=SerializationWriter.writeBytes(dest, pointer, fieldCount);
		
		for (DSField gsField : fields) {
			pointer=gsField.getBytes(dest, pointer);
		}
		
		pointer=SerializationWriter.writeBytes(dest, pointer, stringCount);
		
		for (DSString gsStrings : strings) {
			pointer=gsStrings.getBytes(dest, pointer);
		}
		
		
		pointer=SerializationWriter.writeBytes(dest, pointer, arrayCount);
		
		for (DSArray gsArray : arrays) {
			pointer=gsArray.getBytes(dest, pointer);
		}
		
		return pointer;
	}
	
	public static DSObject Deserialize(byte[] data,int pointer) {
		byte containerType=data[pointer++];
		assert(containerType==CONTAINER_TYPE);
		
		DSObject result=new DSObject();
		result.nameLength= SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		result.name=SerializationWriter.readString(data, pointer, result.nameLength).getBytes();
		pointer+=result.nameLength;
		
		result.size=SerializationWriter.readInt(data, pointer);
		pointer+=4;
		
		result.fieldCount=SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		for (int i = 0; i < result.fieldCount; i++) {
			
			DSField field=DSField.Deserialize(data, pointer);
			result.fields.add(field);
			pointer+=field.getSize();
		}
		
		result.stringCount=SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		for (int i = 0; i < result.stringCount; i++) {
			DSString string=DSString.Deserialize(data, pointer);
			result.strings.add(string);
			pointer+=string.getSize();
		}
		
		
		result.arrayCount=SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		for (int i = 0; i < result.arrayCount; i++) {
			DSArray array=DSArray.Deserialize(data, pointer);
			result.arrays.add(array);
			pointer+=array.getSize();
		}
		
		return result;

	
	}

}
