package com.shag;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.shag.networking.client.Client;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ClientWindwo extends JFrame implements Runnable {

	private JPanel contentPane;

	private JTextField txtMessage;
	JTextArea txtHistory;
	
	private Client client;
	private Thread listen,run;
	private boolean running;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClientWindwo frame = new ClientWindwo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public ClientWindwo(String name, String address, int port) {
		setTitle("Chat window");
//		MainComponent main=new MainComponent();
//		client=new Client(name,address,port);
		boolean connect=client.openConnection(address, port);
		if(!connect) {
			System.err.println("Connection failed");
			console("Connection Failed");
		}
		createWindow();
		console("Attempting a connection to "+ address+" : "+port+", user : "+name);
		String connection="/c/"+name;
		client.send(connection.getBytes());
		running=true;
		run=new Thread(this, "Running");
		run.start();
	}
	
	public void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840,550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50,750, 30,10};
		gbl_contentPane.rowHeights = new int[]{60,470,20};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		txtHistory = new JTextArea();
		txtHistory.setEditable(false);
		JScrollPane scroll=new JScrollPane(txtHistory);
		GridBagConstraints gbc_txtHistory = new GridBagConstraints();
		gbc_txtHistory.insets = new Insets(0, 0, 5, 5);
		gbc_txtHistory.fill = GridBagConstraints.BOTH;
		gbc_txtHistory.gridx = 0;
		gbc_txtHistory.gridy = 0;
		gbc_txtHistory.gridwidth=3;
		gbc_txtHistory.gridheight=2;
		gbc_txtHistory.insets=new Insets(20,20,20,0);
		contentPane.add(scroll, gbc_txtHistory);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
//					send(txtMessage.getText(),true);
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth=2;
		gbc_txtMessage.insets=new Insets(0,20,0,0);
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(txtMessage.getText(),true);
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		gbc_btnSend.insets=new Insets(0,20,0,0);
		contentPane.add(btnSend, gbc_btnSend);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				String disconnect = "/d/"+ client.getID()+"/e/";
				send(disconnect,false);
				running=false;
				client.close();
			}
		});
		
		
		txtMessage.requestFocus();
	}

	public void run() {
		listen();
	}
	
	private void send(String msg,boolean isMsg) {
		if(msg.equals("")) {
			return;
		}
		System.out.println("login window : "+msg);
		if(isMsg) {			
			msg=client.getName()+ ": "+msg;
			msg="/m/"+msg;
		}
		System.out.println("login window : "+msg+"     "+msg.getBytes());
		client.send(msg.getBytes());
		if(isMsg) {			
			txtMessage.setText("");
		}
	}	
	
	public void listen() {
		listen=new Thread("Listen") {
			public void run() {
				while(running) {
					System.out.println("listen");
					String message=client.recive();
					if(message.startsWith("/c")) {
						
						client.setID(Integer.parseInt(message.split("/c/|/e/")[1]));
						console("Successfully Connected to server! ID : "+client.getID());
					}
					else if(message.startsWith("/m/")) {
						String text = message.substring(3);
						text=text.split("/e/")[0];
						console(text);
					}
					else if(message.startsWith("/i/")) {
						String text="/i/"+client.getID()+"/e/";
						send(text, false);
					}
				}
			}
		};
		listen.start();
	}
	
	public void console(String msg) {
		txtHistory.append(msg+"\n\r");
	}
}
