package com.shag.networking.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.shag.untils.UniqueIdentifier;


public class Server implements Runnable{

	private List<ServerClient> clients=new ArrayList<ServerClient>();
	private List<Integer> clientResponse=new ArrayList<Integer>();
	
	int port;
	private DatagramSocket socket;
	private boolean running=false;
	private Thread run,manage, send,recive;
	
	 private static final int MAX_ATTEMPTS=5;
	
	public Server(int port) {
		this.port=port;
		try {
			socket=new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		run=new Thread(this,"Server");
		run.start();
	}
	
	public void run() {
		running=true;
		//manageClients();
		recive();
	}
	
	private void manageClients() {
		manage=new Thread("Manage") {
			public void run() {
				System.out.println("manage");
				while(running) {
					sendMessageToAll("/i/Server");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					for(int i=0;i<clients.size();i++) {
						ServerClient c=clients.get(i);
						if(!clientResponse.contains(c.getID())) {
							if(c.attempt>MAX_ATTEMPTS) {
								disconnect(c.getID(),false);
							}
							else {
								c.attempt++;
							}
						}
						else {
							clientResponse.remove(new Integer(c.getID()));
							c.attempt=0;
						}
					}
				}
			}
		};
		manage.start();
	}
	
	private void recive() {
		recive=new Thread("Recive") {
			public void run() {
				while(running) {
					byte[] data=new byte[1024];
					DatagramPacket packet=new DatagramPacket(data,data.length);
					try {
						socket.receive(packet);
						process(packet);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		
		recive.start();
	}
	
	private void process(DatagramPacket packet) {
		String string=new String(packet.getData());
		System.out.println("server data recived : "+string);
		if(string.startsWith("/c/")) {
			int id=UniqueIdentifier.getIdentifier();
			ServerClient client=new ServerClient(string.substring(3,string.length()),packet.getAddress(),packet.getPort(),id);
			clients.add(client);
			String ID="/c/"+id;
			send(ID,packet.getAddress(),packet.getPort());
		}
		else if(string.startsWith("/m/")) {
			sendMessageToAll(string);
		}
		else if(string.startsWith("/d/")) {
			String id=string.split("/d/|/e/")[1];
			disconnect(Integer.parseInt(id),true);
		}
		else if(string.startsWith("/i/")) {
			clientResponse.add(Integer.parseInt(string.split("/i/|/e/")[1]));
		}
		else {
			System.out.println(string);
		}
	}
	
	private void send(String message, InetAddress address,int port) {
		message+="/e/";
		send(message.getBytes(),address,port);
	}
	
	private void sendMessageToAll(String message) {
		for(int i=0; i<clients.size(); i++) {
			ServerClient client=clients.get(i);
			send(message.getBytes(),client.address,client.port);
		}
	}
	
	private void send(final byte[] data, InetAddress address, int port) {
		send=new Thread("send") {
			public void run() {
				DatagramPacket packet=new DatagramPacket(data,data.length,address,port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		send.start();
	}
	
	private void disconnect(int id, boolean status) {
		ServerClient c=null;
		
		for(int i=0; i<clients.size(); i++) {
			if(clients.get(i).getID()==id) {
				c=clients.get(i);
				clients.remove(i);
				break;
			}
		}
		
		String message="";
		if(status) {
//			message
		}
	}

}
