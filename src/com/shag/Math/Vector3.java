package com.shag.Math;

public class Vector3 {

	public float x;
	public float y;
	public float z;
	
	public static Vector3 one=new Vector3(1,1,1);
	
	public Vector3() {
		x=0;
		y=0;
		z=0;
	}
	
	public Vector3(float x, float y, float z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public Vector3 Plus(Vector3 v) {
		return new Vector3(x+v.x, y+v.y, z+v.z);
	}
	
	public Vector3 Subtract(Vector3 v) {
		return new Vector3(x-v.x, y-v.y, z-v.z);
	}
	
	public Vector3 Mul(float scaler) {
		return new Vector3(x*scaler, y*scaler, z*scaler);
	}
	
	public Vector3 Div(float scaler) {
		return new Vector3(x/scaler, y/scaler, z/scaler);
	}
	
	public Vector3 Normalize() {
		float magnitude=Magnitude();
		return Div(magnitude);
	}
	
	public float Magnitude() {
		return (float) Math.sqrt(SqrtMagnitude());
	}
	
	public float SqrtMagnitude() {
		return (x*x+y*y+z*z);
	}
	
	public float Distance(Vector3 v) {
		Vector3 sub=Subtract(v);
		return sub.Magnitude();
	}
	
	public static float Dot(Vector3 v1, Vector3 v2) {
		return (v1.x*v2.x + v1.y*v2.y + v1.z*v2.z);
	}
	
	public static Vector3 Cross(Vector3 v1, Vector3 v2) {
		float i= (v1.y*v2.z)-(v2.y*v1.z);
		float j=-(v1.x*v2.z)+(v2.x*v1.z);
		float k= (v1.x*v2.y)-(v2.x*v1.y);
		
		return new Vector3(i,j,k);
	}


	

}
