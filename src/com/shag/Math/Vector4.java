package com.shag.Math;

public class Vector4 {
	
	public float x;
	public float y;
	public float z;
	public float w;
	
	public float[] arr=new float[4];  

	public Vector4() {
		x=0;
		y=0;
		z=0;
		w=0;
	}
	
	public Vector4(float x, float y, float z, float w) {
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
	}
	
	public Vector4 Plus(Vector4 v) {
		return new Vector4(x+v.x, y+v.y, z+v.z,w+v.w);
	}
	
	public Vector4 Subtract(Vector4 v) {
		return new Vector4(x-v.x, y-v.y, z-v.z, w-v.w);
	}
	
	public Vector4 Mul(float scaler) {
		return new Vector4(x*scaler, y*scaler, z*scaler,w*scaler);
	}
	
	public Vector4 Div(float scaler) {
		return new Vector4(x/scaler, y/scaler, z/scaler,w/scaler);
	}
	
	public Vector4 Normalize() {
		float magnitude=Magnitude();
		return Div(magnitude);
	}
	
	public float Magnitude() {
		return (float) Math.sqrt(SqrtMagnitude());
	}
	
	public float SqrtMagnitude() {
		return (x*x+y*y+z*z+w*w);
	}
	
	public float Distance(Vector4 v) {
		Vector4 sub=Subtract(v);
		return sub.Magnitude();
	}
	
	public void SetArray() {
		arr[0]=x;
		arr[1]=y;
		arr[2]=z;
		arr[3]=w;
	}
	
	public void SetArrayToAttrib() {
		x=arr[0];
		y=arr[1];
		z=arr[2];
		w=arr[3];
	}

}
