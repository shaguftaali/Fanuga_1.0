package com.shag.networking.server;

public class ServerMain {

	int port;
	Server server;
	
	public ServerMain(int port) {
		this.port=port;
		server=new Server(port);
	}

	public static void main(String[] args) {

		new ServerMain(9999);
		
	}
}
