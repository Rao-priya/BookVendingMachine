package view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 612, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(30, 55, 61, 16);
		frame.getContentPane().add(label);
		
		JButton btnAdmin = new JButton("Admin Login");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminLoginView adminLoginView=new AdminLoginView();
				adminLoginView.frameAdminLogin.setVisible(true);
			}
		});
		btnAdmin.setBounds(108, 428, 117, 29);
		frame.getContentPane().add(btnAdmin);
		
		JLabel lblWelcomeToVending = new JLabel("Welcome To Vending Machine");
		lblWelcomeToVending.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWelcomeToVending.setBounds(155, 6, 337, 37);
		frame.getContentPane().add(lblWelcomeToVending);
		
		JButton btnUser = new JButton("User ");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserView userView=new UserView();
				userView.useFrame.setVisible(true);
			}
		});
		btnUser.setBounds(397, 428, 117, 29);
		frame.getContentPane().add(btnUser);
		
		JLabel imgLabel = new JLabel("New label");
		Image img=new ImageIcon(this.getClass().getResource("/vending.jpg")).getImage();
		imgLabel.setIcon(new ImageIcon(img));
		imgLabel.setBounds(79, 55, 455, 338);
		frame.getContentPane().add(imgLabel);
		
		
		
		
	}

}
