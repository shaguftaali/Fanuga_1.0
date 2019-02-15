package com.shag.game;

import org.lwjgl.input.Keyboard;

import com.shag.Math.Matrix4;
import com.shag.Math.Vector3;
import com.shag.gameObject.Transform;
import com.shag.geometry.Vertex;
import com.shag.graphics.Mesh;
import com.shag.inputSystem.Input;
import com.shag.resourceLoader.ShaderLoader;
import com.shag.shader.Shader;

public class Player {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	
	double temp=0.0f;
	float tempAmount=0.0f;
	
	private String name;
	private int ID;
	private boolean isOwn;
	
	public Player(String name, int ID, boolean isOwn) {
		SetData(name, ID, isOwn);
		SetRenderingData();
	}
	
	public void SetData(String name, int ID, boolean isOwn) {
		this.name=name;
		this.ID=ID;
		this.isOwn=isOwn;
	}
	
	private void SetRenderingData() {
		mesh=new Mesh();
		AddVertices();
		shader=new Shader();
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
		transform.SetPosition(new Vector3(tempAmount,0,0));
	}
	
	public void Input() {
		if(Input.getKeyDown(Keyboard.KEY_SPACE)) {
			System.out.println("We have just pressed up");
		}
		
		if(Input.getKey(Input.KEY_D)) {
			tempAmount+=0.0001;
		}
		else if(Input.getKey(Input.KEY_A)) {
			tempAmount-=0.0001;
		}
	}
		
		public void Render() {
//			System.out.println("player render");
			shader.bind();
			Matrix4 modleMatrix=transform.GetModleMatrix();

			shader.setUniform("transform", modleMatrix);
			
			mesh.draw();
		}

}
