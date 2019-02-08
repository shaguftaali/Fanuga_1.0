package com.shag.serialization;

public class SerializationWriter {
	
	
	public static int writeBytes(byte[] dest, int pointer, byte value) {
		assert(dest.length>pointer+Type.getSize(Type.BYTE));
		dest[pointer++]=value;
		return pointer;
	}

	public static int writeBytes(byte[] dest, int pointer, byte[] src) {
		assert(dest.length>pointer+src.length);
		for(int i=0;i<src.length;i++) {
			dest[pointer++]=src[i];
		}
		return pointer;
	}
	
	public static int writeBytes(byte[] dest, int pointer, short value) {
		assert(dest.length>pointer+Type.getSize(Type.SHORT));
		dest[pointer++]=(byte)((value >> 8) & 0xff);
		dest[pointer++]=(byte)((value >> 0) & 0xff);
		return pointer;
	}
	
	
	public static int writeBytes(byte[] dest, int pointer, char value) {
		assert(dest.length>pointer+Type.getSize(Type.CHAR));
		dest[pointer++]=(byte)((value >> 8) & 0xff);
		dest[pointer++]=(byte)((value >> 0) & 0xff);
		return pointer;
	}
	
	public static int writeBytes(byte[] dest, int pointer, int value) {
		assert(dest.length>pointer+Type.getSize(Type.INTEGER));
		dest[pointer++]=(byte)((value >> 24) & 0xff);
		dest[pointer++]=(byte)((value >> 16) & 0xff);
		dest[pointer++]=(byte)((value >> 8) & 0xff);
		dest[pointer++]=(byte)((value >> 0) & 0xff);
		return pointer;
	}
	
	public static int writeBytes(byte[] dest, int pointer, long value) {
		assert(dest.length>pointer+Type.getSize(Type.LONG));
		dest[pointer++]=(byte)((value >> 56) & 0xff);
		dest[pointer++]=(byte)((value >> 48) & 0xff);
		dest[pointer++]=(byte)((value >> 40) & 0xff);
		dest[pointer++]=(byte)((value >> 32) & 0xff);
		dest[pointer++]=(byte)((value >> 24) & 0xff);
		dest[pointer++]=(byte)((value >> 16) & 0xff);
		dest[pointer++]=(byte)((value >> 8) & 0xff);
		dest[pointer++]=(byte)((value >> 0) & 0xff);
		return pointer;
	}
	
}
