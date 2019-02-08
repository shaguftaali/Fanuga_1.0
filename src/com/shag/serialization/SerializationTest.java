package com.shag.serialization;

public class SerializationTest {
	
	
	public static void Test1() {
		byte[] data=new byte[2];
		
		short number=1243;
//		System.out.printf("0x%x ",number);
//		System.out.println();
//		byte number=10;
		
		int pointer=SerializationWriter.writeBytes(data, 0, number);
		printBytes(data);
	}
	
	static void printBytes(byte[] data) {
		for(int i=0 ;i< data.length; i++) {
			System.out.printf("0x%x ",data[i]);
//			System.out.println();
		}
	}
}
