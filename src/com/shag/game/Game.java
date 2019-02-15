package com.shag.game;


import java.util.ArrayList;
import java.util.List;

import com.shag.Math.Vector3;
import com.shag.inputSystem.*;

public class Game {
	

	private Camera camera;


	Player player;
	
	ArrayList<Player> playerList;
	
	public Game() {
		camera=new Camera(new Vector3(), new Vector3(0,0,-1),new Vector3(0,1,0),1000,0.1f,70f);
		playerList=new ArrayList<Player>();
//		player=new Player("ddd", 17, true);
	}
	
	

	
	public void Update() {
		player.Update();
		
		for (Player listPlayer : playerList) {
			listPlayer.Update();
		}
	}
	
	
	public void Input() {
		Input.update();
		
		player.Input();
		for (Player listPlayer : playerList) {
			listPlayer.Input();
		}
	}
	
	public void Render() {
//		System.out.println("render");
		player.Render();
		
		for (Player listPlayer : playerList) {
			listPlayer.Render();
		}
	}
	
	public void AddNewPlayer(String name, int ID, boolean isOwn) {
		Player player=new Player(name, ID, isOwn);
		playerList.add(player);
	}
	
	public void CreatePlayer(String name, int ID, boolean isOwn) {
		this.player=new Player(name, ID, isOwn);;
	}

}
