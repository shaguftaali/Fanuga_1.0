package com.shag.Math;

public class Vector2 {
	
	public float x;
	public float y;
	
	public Vector2() {
		x=0;
		y=0;
	}
	
	public Vector2(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	public Vector2 Plus(Vector2 v) {
		return new Vector2(x+v.x, y+v.y);
	}
	
	public Vector2 Subtract(Vector2 v) {
		return new Vector2(x-v.x, y-v.y);
	}
	
	public Vector2 Mul(float scaler) {
		return new Vector2(x*scaler, y*scaler);
	}
	
	public Vector2 Div(float scaler) {
		return new Vector2(x/scaler, y/scaler);
	}
	
	public Vector2 Normalize() {
		float magnitude=Magnitude();
		return Div(magnitude);
	}
	
	public float Magnitude() {
		return (float) Math.sqrt(SqrtMagnitude());
	}
	
	public float SqrtMagnitude() {
		return (x*x+y*y);
	}
	
	public float Distance(Vector2 v) {
		Vector2 sub=Subtract(v);
		return sub.Magnitude();
	}
	
	public float Dot(Vector2 v) {
		return (x*v.x + y*v.y);
	}
	
	public float Cross(Vector2 v) {
		 return (x*v.y)-(v.x-y);
	}

}
