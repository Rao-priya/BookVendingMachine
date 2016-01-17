package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AdminLoginView {

	public JFrame frameAdminLogin;
	private JTextField textUserName;
	private JPasswordField passwordField;

	
	/**
	 * Create the application.
	 */
	public AdminLoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameAdminLogin = new JFrame();
		frameAdminLogin.setBounds(100, 100, 450, 300);
		frameAdminLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAdminLogin.getContentPane().setLayout(null);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblAdminLogin.setBounds(159, 16, 170, 28);
		frameAdminLogin.getContentPane().add(lblAdminLogin);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(35, 95, 97, 16);
		frameAdminLogin.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(35, 148, 61, 16);
		frameAdminLogin.getContentPane().add(lblPassword);
		
		textUserName = new JTextField();
		textUserName.setBounds(195, 89, 134, 28);
		frameAdminLogin.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 142, 134, 28);
		frameAdminLogin.getContentPane().add(passwordField);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textUserName.getText();
				String password = passwordField.getText();
				if("admin".equalsIgnoreCase(id) && "admin".equalsIgnoreCase(password)){
					frameAdminLogin.dispose();
					AdminView ah=new AdminView();
					ah.frame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null,"user id/ password doesnot match");
					textUserName.setText("");
					passwordField.setText("");
				}
			}
		});
		btnLogin.setBounds(147, 209, 117, 29);
		frameAdminLogin.getContentPane().add(btnLogin);
	}
}
