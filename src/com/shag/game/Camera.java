package com.shag.game;

import com.shag.MainComponent;
import com.shag.Math.Matrix4;
import com.shag.Math.Vector3;
import com.shag.Math.Vector4;
import com.shag.untils.Constant;

public class Camera {
	
	private Vector3 position;
	private Vector3 eyeDirection;
	private Vector3 up;
	
	private float far;
	private float near;
	private float FOV;
	
	private boolean isDirty;
	
	private Matrix4 viewMatrix;
	private Matrix4 projectionMatrix;
	
	

	public Camera(Vector3 pos, Vector3 target, Vector3 up, float far, float near, float FOV) {
		this.position=pos;
		this.up=up;
		this.far=far;
		this.near=near;
		this.FOV=FOV;
		eyeDirection=target.Subtract(position);
		SetLookAtMatrix(position,this.up);
		SetProjectionMatrix();
	}
	
	public void Update() {
		if(isDirty) {
			SetLookAtMatrix(position, up);
			SetProjectionMatrix();
			isDirty=false;
		}
	}
	
	private void SetLookAtMatrix(Vector3 pos, Vector3 up) {
		Vector3 xAxis=Vector3.Cross(eyeDirection,up).Normalize();
		
		Vector3 yAxis=Vector3.Cross(xAxis, eyeDirection).Normalize();
		
		float ex= -Vector3.Dot(xAxis,position);
		float ey= -Vector3.Dot(yAxis,position);
		float ez= Vector3.Dot(eyeDirection,position);
		
//		viewMatrix= new Matrix4(
//				new Vector4(		xAxis.x,		 xAxis.y,		  xAxis.z, 0.0f),
//				new Vector4(		yAxis.x,		 yAxis.y,		  yAxis.z, 0.0f),
//				new Vector4(-eyeDirection.x, -eyeDirection.y, -eyeDirection.z, 0.0f),
//				new Vector4(			 ex,		      ey,		       ez, 1.0f)
//				
//				
//				);
		
		viewMatrix=new Matrix4(
				new Vector4(xAxis.x,yAxis.x,-eyeDirection.x,ex),
				new Vector4( xAxis.y,yAxis.y,-eyeDirection.y,ey),
				new Vector4(xAxis.z, yAxis.z,-eyeDirection.z,ez),
				new Vector4(0,0,0,1)
				
				
				);
				
				
		viewMatrix.SetElement();
		
	}
	
	
	private void SetProjectionMatrix() {
//		float aspectRatio=MainComponent.WIDTH/MainComponent.HEIGHT;
		float a=800f/600f;
//		float radians=FOV*(Constant.PI/180);
		float tan=(float)Math.tan(FOV/2);
		float A=-(far+near)/(-far+near);
		float B=(2*far*near)/(-far+near);
		
		projectionMatrix=new Matrix4(
				new Vector4(1/(tan*a),  0f, 0f, 0f),
				new Vector4( 0f, 1/tan,  0f,0f),
				new Vector4( 0f,   0f,  A, B),
				new Vector4( 0f,   0f,  1, 0)
				
				);
		
		projectionMatrix.SetElement();
	}
	
	public Matrix4 GetProjectionMatrix() {
		return projectionMatrix;
	}
	
	public Matrix4 GetViewMatrix() {
		return viewMatrix;
	}
	
	public void SetPosition(Vector3 pos) {
		isDirty=true;
		this.position=pos;
	}
	
	public void SetUp(Vector3 pos) {
		isDirty=true;
		this.up=up;
	}
	
	public void SetTarget(Vector3 target) {
		isDirty=true;
		this.eyeDirection=target.Subtract(position);
	}

}
