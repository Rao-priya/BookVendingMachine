package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JButton;







import model.VendingMachine;
import model.SchoolVM;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SchoolView {

	public JFrame frameSchoolView;

	

	/**
	 * Create the application.
	 * @param i 
	 */
	public SchoolView(int i) {
		initialize(i);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int vmID) {
		final VendingMachine schoolVM=new SchoolVM(vmID);
		frameSchoolView = new JFrame();
		frameSchoolView.setBounds(100, 100, 653, 461);
		frameSchoolView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameSchoolView.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to School Vending Machine");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("MS Gothic", Font.BOLD, 16));
		lblNewLabel.setBounds(163, 40, 327, 16);
		frameSchoolView.getContentPane().add(lblNewLabel);
		
		JButton btnDonate = new JButton("DONATE");
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonationView donationView=new DonationView(schoolVM,null);
				donationView.frameDonation.setVisible(true);
			}
		});
		btnDonate.setBounds(6, 147, 117, 29);
		frameSchoolView.getContentPane().add(btnDonate);
		
		JButton btnExchange = new JButton("EXCHANGE");
		btnExchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DonationView dView=new DonationView(schoolVM,"Exchange");
				//dView.setNavigation("Exchange");
				dView.frameDonation.setVisible(true);
				
				
			}
		});
		btnExchange.setBounds(6, 278, 117, 29);
		frameSchoolView.getContentPane().add(btnExchange);
		
		JButton btnGetVendingCard = new JButton("GET VENDING CARD");
		btnGetVendingCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewVendingCardView window = new NewVendingCardView(schoolVM);
				window.vendingcardFrame.setVisible(true);
			}
		});
		btnGetVendingCard.setBounds(481, 147, 155, 29);
		frameSchoolView.getContentPane().add(btnGetVendingCard);
		
		JButton btnViewCardBalance = new JButton("VIEW/RECHARGE CARD");
		btnViewCardBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechargeCardView rechargeCard=new RechargeCardView(schoolVM);
				rechargeCard.frameBalance.setVisible(true);
			}
		});
		btnViewCardBalance.setBounds(470, 278, 177, 29);
		frameSchoolView.getContentPane().add(btnViewCardBalance);
		
		JLabel imgLabel = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/Boy.jpg")).getImage();
		imgLabel.setIcon(new ImageIcon(img));
		imgLabel.setBounds(176, 127, 334, 260);
		frameSchoolView.getContentPane().add(imgLabel);
	}
}
