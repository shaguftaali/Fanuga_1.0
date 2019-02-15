package com.shag.serialization;

public class DSArray extends DSBase {

	public static final byte CONTAINER_TYPE = ContainerType.ARRAY;
	
	public byte type;
	public int count;
	
	private byte[] byteData;
	private short[] shortData;
	private char[] charData;
	private int[] intData;
	private long[] longData;
	private float[] floatData;
	private double[] doubleData;
	private boolean[] booleanData;
	
	private DSArray() {
		size+=1+1+4;
	}
	
	
	public int getBytes(byte[] dest, int pointer) {
		pointer=SerializationWriter.writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer=SerializationWriter.writeBytes(dest, pointer, nameLength);
		pointer=SerializationWriter.writeBytes(dest, pointer, name);
		pointer=SerializationWriter.writeBytes(dest, pointer, type);
		pointer=SerializationWriter.writeBytes(dest, pointer, count);
		
		switch(type) {
		case Type.BYTE:
			pointer=SerializationWriter.writeBytes(dest, pointer, byteData);			
			break;
			
		case Type.CHAR:
			pointer=SerializationWriter.writeBytes(dest, pointer, charData);			
			break;
			
		case Type.SHORT:
			pointer=SerializationWriter.writeBytes(dest, pointer, shortData);			
			break;
			
		case Type.INTEGER:
			pointer=SerializationWriter.writeBytes(dest, pointer, intData);			
			break;
			
		case Type.LONG:
			pointer=SerializationWriter.writeBytes(dest, pointer, longData);			
			break;
			
		case Type.FLOAT:
			pointer=SerializationWriter.writeBytes(dest, pointer, floatData);			
			break;
			
		case Type.DOUBLE:
			pointer=SerializationWriter.writeBytes(dest, pointer, doubleData);			
			break;
			
		case Type.BOOLEAN:
			pointer=SerializationWriter.writeBytes(dest, pointer, booleanData);			
			break;

		default:
			break;
		}
		
		return pointer;
	}
	
	private void updateSize() {
		size+=getDataSize();
	}
	
	public int getSize() {
		return size;
	}
	
	public int getDataSize() {
		switch (type) {
			case Type.BYTE:		
				return byteData.length*Type.getSize(Type.BYTE);
				
			case Type.CHAR:
				return charData.length*Type.getSize(Type.CHAR);
				
			case Type.SHORT:
				return shortData.length*Type.getSize(Type.SHORT);
				
			case Type.INTEGER:
				return intData.length*Type.getSize(Type.INTEGER);
				
			case Type.LONG:
				return longData.length*Type.getSize(Type.LONG);
				
			case Type.FLOAT:
				return floatData.length*Type.getSize(Type.FLOAT);
				
			case Type.DOUBLE:
				return doubleData.length*Type.getSize(Type.DOUBLE);
				
			case Type.BOOLEAN:
				return booleanData.length*Type.getSize(Type.BOOLEAN);
		
		}
		return 0;
	}
	
	

	public static DSArray Byte(String name, byte[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.BYTE;
		array.count=data.length;
		array.byteData=data;
		array.updateSize();
		return array;
	}
	
	public static DSArray Char(String name, char[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.CHAR;
		array.count=data.length;
		array.charData=data;
		array.updateSize();
		return array;
	}
	
	public static DSArray Short(String name, short[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.SHORT;
		array.count=data.length;
		array.shortData=data;
		array.updateSize();
		return array;
	}
	
	public static DSArray Integer(String name, int[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.INTEGER;
		array.count=data.length;
		array.intData=data;
		array.updateSize();
		return array;
	}
	
	public static DSArray Long(String name, long[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.LONG;
		array.count=data.length;
		array.longData=data;
		array.updateSize();
		return array;
	}
	
	public static DSArray Float(String name, float[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.FLOAT;
		array.count=data.length;
		array.floatData=data;
		array.updateSize();
		return array;
	}
	
	
	public static DSArray Double(String name, double[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.DOUBLE;
		array.count=data.length;
		array.doubleData=data;
		array.updateSize();
		return array;
	}
	
	public static DSArray Boolean(String name, boolean[] data) {
		DSArray array=new DSArray();
		array.setName(name);
		array.type=Type.BOOLEAN;
		array.count=data.length;
		array.booleanData=data;
		array.updateSize();
		return array;
	}
	
	public static DSArray Deserialize(byte[] data, int pointer) {
		byte containerType= data[pointer++];
		assert(containerType==CONTAINER_TYPE);
		
		DSArray result=new DSArray();
		result.nameLength=SerializationWriter.readShort(data, pointer);
		pointer+=2;
		
		result.name=SerializationWriter.readString(data, pointer,result.nameLength).getBytes();
		pointer+=result.nameLength;
		
		result.size=SerializationWriter.readInt(data, pointer);
		pointer+=4;
		
		result.type=data[pointer++];
		
		result.count=SerializationWriter.readInt(data, pointer);
		pointer+=4;
		
		switch (result.type) {
		case Type.BYTE:
			result.byteData=new byte[result.count];
			SerializationWriter.readBytes(data, pointer, result.byteData);	
			break;
			
			
		case Type.CHAR:
			result.charData=new char[result.count];
			SerializationWriter.readChars(data, pointer, result.charData);				
			break;
			
		case Type.SHORT:
			result.shortData=new short[result.count];
			SerializationWriter.readShorts(data, pointer, result.shortData);				
			break;
			
		case Type.INTEGER:
			result.intData=new int[result.count];
			SerializationWriter.readInts(data, pointer, result.intData);				
			break;
			
		case Type.LONG:
			result.longData=new long[result.count];
			SerializationWriter.readLongs(data, pointer, result.longData);				
			break;
			
		case Type.FLOAT:
			result.floatData=new float[result.count];
			SerializationWriter.readFloats(data, pointer, result.floatData);				
			break;
			
		case Type.DOUBLE:
			result.doubleData=new double[result.count];
			SerializationWriter.readDoubles(data, pointer, result.doubleData);				
			break;
			
		case Type.BOOLEAN:
			result.booleanData=new boolean[result.count];
			SerializationWriter.readBooleans(data, pointer, result.booleanData);				
			break;

		default:
			break;
		
		}
		
		pointer+=result.count*Type.getSize(result.type);
		return result;
	}
	
}
