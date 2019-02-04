package com.shag.networking.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	
	private String name;
	private String address;
	private DatagramSocket socket;
	private int port;
	private InetAddress ip;
	
	private Thread sendThread,listen,run;
	private int ID;
	private boolean running;
	
	

	public Client(String name, String address, int port) {
		this.name=name;
		this.address=address;
		this.port=port;
//		boolean connect=openConnection(address, port);
//		System.out.println(("connection "+connect));
//		if(!connect) {
//			System.err.println("Connection failed");
////			console("Connection Failed");
//		}
//		running=true;
//		run=new Thread("Client Running");
//		run.start();
	}
	
//	public void run() {
//		listen();
//	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getPort() {
		return port;
	}
	
//	public void listen() {
//		listen=new Thread("Listen") {
//			public void run() {
//				while(running) {
//					String message=recive();
//					if(message.startsWith("/c")) {
//						ID=Integer.parseInt(message.split("/c/|/e/")[1]);
//						System.out.println("Successfully Connected to server! ID : "+getID());
//					}
//					else if(message.startsWith("/m/")) {
//						String text = message.substring(3);
//						text=text.split("/e/")[0];
//						System.out.println(text);
//					}
//					else if(message.startsWith("/i/")) {
//						String text="/i/"+getID()+"/e/";
//						System.out.println("client : listening");
//						send(text, false);
//					}
//				}
//			}
//		};
//		listen.start();
//	}
	
	public boolean openConnection(String address, int port) {
		System.out.println("add "+address+"\t"+port);
		try {
			socket=new DatagramSocket();
			try {
				ip=InetAddress.getByName(address);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return false;
			}
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void send(String msg,boolean isMsg) {
		if(msg.endsWith("")) {
			return;
		}
		
		if(isMsg) {			
			msg=getName()+ ": "+msg;
			msg="/m/"+msg;
		}
		send(msg.getBytes());
	}
	
	public void send(final byte[] data) {
		sendThread=new Thread("Send") {
			public void run() {
				DatagramPacket packet=new DatagramPacket(data,data.length,ip,port);
				try {
					System.out.println("client : data send ");
					socket.send(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		sendThread.start();
	}
	
	
	public String recive() {
		byte[] data = new byte[1024];
		DatagramPacket packet=new DatagramPacket(data,data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		String msg=new String(data);
		return msg;
	}
	
	public void close() {
		new Thread() {
			public void run() {
				synchronized (socket) {
					socket.close();
				}
			}
		}.start();
	}
	
	public void setID(int id) {
		this.ID=id;
	}
	
	public int getID() {
		return ID;
	}

}
