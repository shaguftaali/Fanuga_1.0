package com.shag.game;

import org.lwjgl.input.Keyboard;

import com.shag.Math.Matrix4;
import com.shag.Math.Vector3;
import com.shag.Math.Vector4;
import com.shag.gameObject.Transform;
import com.shag.geometry.Vertex;
import com.shag.graphics.Mesh;
import com.shag.resourceLoader.ShaderLoader;
import com.shag.shader.Shader;
import com.shag.inputSystem.*;

public class Game {
	
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	double temp=0.0f;
	float tempAmount=0.0f;

	public Game() {
		mesh=new Mesh();
		AddVertices();
		shader=new Shader();
		camera=new Camera(new Vector3(), new Vector3(0,0,-1),new Vector3(0,1,0),1000,0.1f,70f);
		transform=new Transform();
		transform.SetScale(new Vector3(0.5f,0.5f,0.5f));

		shader.addVertexShader(ShaderLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ShaderLoader.loadShader("basicFragment.fs"));
		shader.compileShader();
		shader.addUniforms("transform");
	}
	
	private void AddVertices() {
		Vertex[] data = new Vertex[] {
				new Vertex(new Vector3(-1,-1,0)),
				new Vertex(new Vector3(0,1,0)),
				new Vertex(new Vector3(1,-1,0))
		};
		
		mesh.addVertices(data);
	}
	
	public void Update() {
//		temp+=0.0001;
//		tempAmount=(float)Math.sin(temp);
		transform.SetPosition(new Vector3(tempAmount,0,0));
	}
	
	
	public void Input() {
		Input.update();
//		System.out.println("Input");
		if(Input.getKeyDown(Keyboard.KEY_SPACE)) {
			System.out.println("We have just pressed up");
		}
		
		if(Input.getKey(Input.KEY_D)) {
			tempAmount+=0.0001;
		}
		else if(Input.getKey(Input.KEY_A)) {
			tempAmount-=0.0001;
		}
		
//		transform.SetPosition(new Vector3(tempAmount,0,0));
	}
	
	public void Render() {
		shader.bind();
		Matrix4 modleMatrix=transform.GetModleMatrix();
//		Matrix4 projectionMatrix=camera.GetProjectionMatrix();
//		Matrix4 viewMatrix=camera.GetViewMatrix();
		
//		Matrix4 m1=projectionMatrix.Mul(viewMatrix);
//		Matrix4 m=modleMatrix.Mul(m1);

		shader.setUniform("transform", modleMatrix);
		
//		shader.setUniform("transform", camera.GetProjectionMatrix());
		//shader.setUniformf("uniformFloat", 1);
		mesh.draw();
	}

}
