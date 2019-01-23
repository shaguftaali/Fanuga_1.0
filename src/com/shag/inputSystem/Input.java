package com.shag.inputSystem;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.shag.Math.Vector2;

public class Input {
	
	public static final int NUM_KEYCODES=256;
	public static final int NUM_MOUSEBUTTONS=5;
	

	private static ArrayList<Integer> currentKey=new ArrayList<Integer>();
	private static ArrayList<Integer> downKey=new ArrayList<Integer>();
	
	private static ArrayList<Integer> upKey=new ArrayList<Integer>();
	
	private static ArrayList<Integer> currentMouse=new ArrayList<Integer>();
	private static ArrayList<Integer> downMouse=new ArrayList<Integer>();
	private static ArrayList<Integer> upMouse=new ArrayList<Integer>();
	
	
	public static void update() {
		upMouse.clear();
		
		for(int i=0; i<NUM_MOUSEBUTTONS;i++) {
			if(!getMouse(i) && currentMouse.contains(i) ) {
				upMouse.add(i);
			}
		}
		
		
		
		downMouse.clear();
		
		for(int i=0; i<NUM_MOUSEBUTTONS;i++) {
			if(getMouse(i) && currentMouse.contains(i)) {
				downMouse.add(i);
			}
		}
		
		currentMouse.clear();
		
		for(int i=0;i<NUM_MOUSEBUTTONS; i++) {
			if(getMouse(i)) {
				currentMouse.add(i);
			}
		}
		
		upKey.clear();
		
		for(int i=0;i<NUM_KEYCODES;i++) {
			if(!getKey(i) && currentKey.contains(i)) {
				upKey.add(i);
			}
		}
		
		downKey.clear();
		
		for(int i=0;i<NUM_KEYCODES;i++) {
			if(getKey(i) && currentKey.contains(i)) {
				downKey.add(i);
			}
		}
		
		currentKey.clear();
		
		for(int i=0; i<NUM_KEYCODES;i++) {
			if(getKey(i)) {
				currentKey.add(i);
			}
		}
	}

	public static boolean getMouse(int mouseButton) {
		return Mouse.isButtonDown(mouseButton);
	}
	
	public static boolean getMouseDown(int mouseButton) {
		return downMouse.contains(mouseButton);
	}
	
	public static boolean getMouseUp(int mouseButton) {
		return upMouse.contains(mouseButton);
	}
	
	public static Vector2 getMousePosition() {
		return new Vector2(Mouse.getX(),Mouse.getY());
	}
	
	public static boolean getKey(int keyCode) {
		return Keyboard.isKeyDown(keyCode);
	}
	
	public static boolean getKeyDown(int keyCode) {
		return downKey.contains(keyCode);
	}
	
	public static boolean getKeyUp(int keyCode) {
		return upKey.contains(keyCode);
	}
}
