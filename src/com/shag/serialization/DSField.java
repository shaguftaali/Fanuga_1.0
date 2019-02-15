package com.shag.serialization;

public class DSField  extends DSBase{

	public static final byte CONTAINER_TYPE=ContainerType.FIELD;
	public byte type;
	public byte[] data;
	
	public int getBytes(byte[] dest, int pointer) {
		pointer=SerializationWriter.writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer=SerializationWriter.writeBytes(dest, pointer, nameLength);
		pointer=SerializationWriter.writeBytes(dest, pointer, name);
		pointer=SerializationWriter.writeBytes(dest, pointer, type);
		pointer=SerializationWriter.writeBytes(dest, pointer, data);
		return pointer;
	}
	
	public int getSize() {
		assert(data.length==Type.getSize(type));
		return 1+2+name.length+1+data.length;
	}
	
	public static DSField Byte(String name, byte value) {
		DSField field = new DSField();
		field.setName(name);
		field.type=Type.BYTE;
		field.data=new byte[Type.getSize(field.type)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	

	public static DSField Character(String name, char value) {
		DSField field=new DSField();
		field.setName(name);
		field.type=Type.CHAR;
		field.data=new byte[Type.getSize(Type.CHAR)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	
	public static DSField Short(String name, short value) {
		DSField field=new DSField();
		field.setName(name);
		field.type=Type.SHORT;
		field.data=new byte[Type.getSize(Type.SHORT)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	
	public static DSField Integer(String name, int value) {
		DSField field=new DSField();
		field.setName(name);
		field.type=Type.INTEGER;
		field.data=new byte[Type.getSize(Type.INTEGER)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	
	public static DSField Long(String name, long value) {
		DSField field=new DSField();
		field.setName(name);
		field.type=Type.LONG;
		field.data=new byte[Type.getSize(Type.LONG)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	
	public static DSField Float(String name, float value) {
		DSField field=new DSField();
		field.setName(name);
		field.type=Type.FLOAT;
		field.data=new byte[Type.getSize(Type.FLOAT)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	
	public static DSField Double(String name, double value) {
		DSField field=new DSField();
		field.setName(name);
		field.type=Type.DOUBLE;
		field.data=new byte[Type.getSize(Type.DOUBLE)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	
	public static DSField Boolean(String name, boolean value) {
		DSField field=new DSField();
		field.setName(name);
		field.type=Type.BOOLEAN;
		field.data=new byte[Type.getSize(Type.BOOLEAN)];
		SerializationWriter.writeBytes(field.data, 0, value);
		return field;
	}
	
	public static DSField Deserialize (byte[] data, int pointer) {
		byte containerType=data[pointer++];
		assert(containerType==CONTAINER_TYPE);
		
		DSField result = new DSField();
		result.nameLength=SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		result.name=SerializationWriter.readString(data, pointer, result.nameLength).getBytes();
		pointer+=result.nameLength;
		
		result.type=data[pointer++];
		
		result.data=new byte[Type.getSize(result.type)];
		SerializationWriter.readBytes(data, pointer,result.data);
		pointer+=Type.getSize(result.type);
		
		return result;
		
	}
}
