package com.shag.gameObject;

import com.shag.Math.Matrix4;
import com.shag.Math.Vector3;
import com.shag.Math.Vector4;
import com.shag.untils.Constant;

public class Transform {
	
	private Vector3 position;
	private Vector3 rotation;
	private Vector3 scale;

	private boolean isDirty;
	private Matrix4 modelMatrix;
	
	public Transform() {
		position=new Vector3();
		rotation=new Vector3();
		scale=Vector3.one;
		SetModleMatrix();
		
	}
	
	public Transform(Vector3 position) {
		this.position=position;
		rotation=new Vector3();
		scale=Vector3.one;
		SetModleMatrix();
	}
	
	public Transform(Vector3 position, Vector3 rotation) {
		this.position=position;
		this.rotation=rotation;
		scale=Vector3.one;
		SetModleMatrix();
	}

	public Transform(Vector3 position, Vector3 rotation,Vector3 scale) {
		this.position=position;
		this.rotation=rotation;
		this.scale=scale;
		SetModleMatrix();
	}
	
	public void SetPosition(Vector3 a_position)
	{
	    isDirty = true;
	    position = a_position;
	}

	public void SetRotation(Vector3 a_Roatation)
	{
	    isDirty = true;
	    rotation = a_Roatation;
	}

	public void SetScale(Vector3 a_scale)
	{
	    isDirty = true;
	    scale = a_scale;
	}
	
	public Matrix4 GetModleMatrix() {
		if(isDirty) {
			SetModleMatrix();
		}
		return modelMatrix;
	}
	
	private void SetModleMatrix() {
		  Vector3 radian = rotation.Mul(Constant.PI/ 180.0f);
//		   Matrix4 TranslationMat=new Matrix4(
//		    
//		        new Vector4(1,0,0,0),
//		        new Vector4(0,1,0,0),
//		        new Vector4(0,0,1,0),
//		        new Vector4(position.x,position.y,position.z,1)
//		    );
		  
		  Matrix4 TranslationMat=new Matrix4(
				    
			        new Vector4(1,0,0,position.x),
			        new Vector4(0,1,0,position.y),
			        new Vector4(0,0,1,position.z),
			        new Vector4(0,0,0,         1)
			    );

		     Matrix4 ScalMat=new Matrix4(
		    
		    	new Vector4(scale.x,      0,       0, 0),
		    	new Vector4(	  0,scale.y,       0, 0),
		    	new Vector4(	  0,      0, scale.z, 0),
		    	new Vector4(	  0,      0,       0, 1)
		    );

		     Matrix4 X_RotationMat=new Matrix4(
		    
		    	 new Vector4(1,              			 0,              			0, 0),
		    	 new Vector4(0, (float)Math.cos(radian.x),-(float)Math.sin(radian.x), 0),
		    	 new Vector4(0, (float)Math.sin(radian.x),(float) Math.cos(radian.x), 0),
		    	 new Vector4(0,              			 0,                         0, 1)
		    );

		    Matrix4 Y_RotationMat=new Matrix4(
		    
		    	new Vector4( (float)Math.cos(radian.y), 0, (float)Math.sin(radian.y), 0),
		    	new Vector4(						 0, 1,                         0, 0),
		    	new Vector4(-(float)Math.sin(radian.y), 0, (float)Math.cos(radian.y), 0),
		    	new Vector4(						 0, 0,                         0, 1)
		    );

		    Matrix4 Z_RotationMat=new Matrix4(
		    
		    	new Vector4( (float)Math.cos(radian.z), -(float)Math.sin(radian.z), 0, 0),
		    	new Vector4((float)Math.sin(radian.z), (float)Math.cos(radian.z), 0, 0),
		    	new Vector4(						 0,             			0, 1, 0),
		    	new Vector4(						 0,			    			0, 0, 1)
		    );

		    Matrix4 M1 = ScalMat.Mul(X_RotationMat);
		    Matrix4 M2 = M1.Mul(Y_RotationMat);
		    Matrix4 M3 = M2.Mul(Z_RotationMat);
		    modelMatrix = M3.Mul(TranslationMat);
	}
	
	public void Update() {
		
	}

}
