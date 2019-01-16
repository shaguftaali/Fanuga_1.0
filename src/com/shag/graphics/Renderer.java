package com.shag.graphics;

import javax.xml.crypto.dsig.Transform;

import com.shag.game.Camera;

public class Renderer {
	
	private MeshFilter meshFilter;
	private Mesh mesh;
	private RenderMode renderMode;
	private Material material;
	

	public Renderer() {
		mesh=null;
		material=null;
	}
	
	public Renderer(Mesh mesh, Material mat) {
		this.mesh=mesh;
		this.material=mat;
		meshFilter=new MeshFilter(this.mesh);
		
	}
	
	public void SetMesh(Mesh mesh) {
		this.mesh=mesh;
	}
	
	public void SetMaterial(Material material) {
		this.material=material;
	}
	
	public void SetMeshFilter(MeshFilter meshFilter) {
		if(mesh==null) {
			this.meshFilter=meshFilter;
		}
	}
	
	public void Render(Camera camera, Transform transform) {
		camera.Update();
		//materia;
		
	}

}
