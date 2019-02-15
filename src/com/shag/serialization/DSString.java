package com.shag.serialization;

public class DSString extends DSBase{
	
	public static final byte CONTAINER_TYPE=ContainerType.STRING;       //Data Storage Type (field (primtive data type), Array, Object)

	public int count;
	private char[] characters;
	
	public DSString() {
		size+=1+4;
	}

	public String getString() {
		return new String(characters);
	}
	
	public int getBytes(byte[] dest , int pointer) {
		pointer=SerializationWriter.writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer=SerializationWriter.writeBytes(dest, pointer, nameLength);
		pointer=SerializationWriter.writeBytes(dest, pointer, name);
		pointer=SerializationWriter.writeBytes(dest, pointer, size);
		pointer=SerializationWriter.writeBytes(dest, pointer, count);
		pointer=SerializationWriter.writeBytes(dest, pointer, characters);
		
		return pointer;
	}
	
	private void updateSize() {
		size+=getDataSize();
	}
	
	
	public int getSize() {
		return size;
	}
	
	public int getDataSize() {
		return characters.length*Type.getSize(Type.CHAR);
		
	}
	
	public static DSString Create(String name, String data) {
		DSString string=new DSString();
		string.setName(name);
		string.count=data.length();
		string.characters=data.toCharArray();
		string.updateSize();
		return string;
	}
	
	public static DSString Deserialize(byte[] data,int pointer) {
		byte containerType=data[pointer++];
		assert(containerType==CONTAINER_TYPE);
		
		DSString result=new DSString();
		result.nameLength=SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		result.name=SerializationWriter.readString(data, pointer,result.nameLength).getBytes();
		pointer+=result.nameLength;
		
		result.size=SerializationWriter.readInt(data, pointer);
		pointer+=4;
		
		result.count=SerializationWriter.readInt(data, pointer);
		pointer+=4;
		
		
		result.characters=new char[result.count];
		SerializationWriter.readChars(data, pointer, result.characters);
		pointer+=result.count* Type.getSize(Type.CHAR);
		return result;
	}
}
