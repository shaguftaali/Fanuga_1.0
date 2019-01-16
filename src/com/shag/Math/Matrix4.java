package com.shag.Math;

public class Matrix4 {
	
	float[] element= new float [4*4];
	float[][] mat= new float [4][4];
	
	public static Matrix4 IdentityMat = new Matrix4(
			
			new Vector4(1,0,0,0),
			new Vector4(0,1,0,0),
			new Vector4(0,0,1,0),
			new Vector4(0,0,0,1)
	);
			

	public Matrix4() {
		SetMatrixRow(0, new Vector4());
		SetMatrixRow(1, new Vector4());
		SetMatrixRow(2, new Vector4());
		SetMatrixRow(3, new Vector4());
		SetElement();
	}
	
	public Matrix4(float[][] arg) {
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				mat[i][j]=arg[i][j];
			}
		}
		
		SetElement();
	}
	
	public Matrix4(Vector4 v1, Vector4 v2, Vector4 v3, Vector4 v4) {
		SetMatrixRow(0,v1);
		SetMatrixRow(1,v2);
		SetMatrixRow(2,v3);
		SetMatrixRow(3,v4);
		SetElement();
	}
	
	public Matrix4 Transpose() {
		Matrix4 mat4=new Matrix4();
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				mat4.mat[j][i]=mat[i][j];
			}
		}
		mat4.SetElement();
		return mat4;
	}
	
	public Matrix4 Add(Matrix4 mat1) {
		Matrix4 mat4=new Matrix4();
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				mat4.mat[i][j]=mat[i][j]+mat1.mat[i][j];
			}
		}
		mat4.SetElement();
		return mat4;
	}
	
	public Matrix4 Sub(Matrix4 mat1) {
		Matrix4 mat4=new Matrix4();
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				mat4.mat[i][j]=mat[i][j]-mat1.mat[i][j];
			}
		}
		mat4.SetElement();
		return mat4;
	}
	
	
	public Matrix4 Mul(Matrix4 mat1) {
		Matrix4 mat4=new Matrix4();
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				for(int k=0; k<4; k++) {
					
					mat4.mat[i][j]+=mat[i][k]*mat1.mat[k][j];
				}
			}
		}
		mat4.SetElement();
		return mat4;
	}
	
	public Matrix4 Mul(float scaler) {
		Matrix4 mat4=new Matrix4();
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				mat4.mat[i][j]=mat[i][j]-scaler;
			}
		}
		mat4.SetElement();
		return mat4;
	}
	
	public Vector4 Mul(Vector4 vec) {
		Vector4 result=new Vector4();
		for(int i=0; i<4;i++) {
			for(int j=0; j<4;j++) {
				result.arr[i]+=mat[i][j]*vec.arr[j];
			}
		}
		result.SetArrayToAttrib();;
		return result;
	}

	
	
	public float[] get(int index){
		 return mat[index];
	}
	
	private void SetMatrixRow(int row, Vector4 vec4) {
		mat[row][0]=vec4.x;
		mat[row][1]=vec4.y;
		mat[row][2]=vec4.z;
		mat[row][3]=vec4.w;
	}
	
	public void SetElement() {
		int index=0;
		for(int i=0;i<4;i++) {
			for(int j=0; j<4 ; j++) {
				element[index]=mat[i][j];
				index++;
			}
		}
	}

}
