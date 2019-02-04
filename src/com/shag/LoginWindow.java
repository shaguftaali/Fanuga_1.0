package com.shag;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JButton;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void login(String name, String address,int port) {
		dispose();
		new ClientWindwo(name, address, port);
		System.out.println(name+" , "+address+" , "+port);
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(68, 44, 239, 14);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setBounds(68, 76, 239, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("IP Address");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(68, 129, 239, 14);
		contentPane.add(lblNewLabel_1);
		
		txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddress.setBounds(68, 164, 239, 20);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Port");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(68, 226, 239, 14);
		contentPane.add(lblNewLabel_2);
		
		txtPort = new JTextField();
		txtPort.setHorizontalAlignment(SwingConstants.CENTER);
		txtPort.setBounds(68, 251, 237, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name =txtName.getText();
				String address=txtAddress.getText();
				int port=Integer.parseInt(txtPort.getText());
				login(name, address, port);
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(145, 320, 89, 23);
		contentPane.add(btnLogin);
	}
}
