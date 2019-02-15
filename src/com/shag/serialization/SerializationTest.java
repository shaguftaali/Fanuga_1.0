package com.shag.serialization;

public class SerializationTest {
	
	
	public static void Test1() {
//		byte[] data=new byte[1];
		
//		byte number=10;
//		int pointer=0;
//		pointer=SerializationWriter.writeBytes(data, pointer, number);
//		pointer=SerializationWriter.writeBytes(data, pointer, number);
//		printBytes(data);
		
//		byte[] byteData=new byte[] {
//				0x0,
//				0x0,
//				0x27,
//				0x10
//			};
//		
//		System.out.println(SerializationWriter.readInt(byteData, 0));
		
		DSField field=DSField.Long("Test", 10);
		byte[] data =new byte[field.getSize()];
		field.getBytes(data, 0);
		printBytes(data);
	}
	
	static void printBytes(byte[] data) {
		for(int i=0 ;i< data.length; i++) {
			System.out.printf("0x%x ",data[i]);
		}
			System.out.println();
	}
	
	public static void print(byte[] data) {
		for(int i=0; i<data.length; i++) {
			System.out.println(data[i]);
		}
	}

}
