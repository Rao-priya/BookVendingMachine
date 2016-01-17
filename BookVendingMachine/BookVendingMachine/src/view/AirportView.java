package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import model.AirportVM;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

public class AirportView {

	public JFrame frameAiport;


	/**
	 * Create the application.
	 */
	public AirportView(int vmID ) {
		initialize(vmID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int vmID) {
		final AirportVM airportVM=new AirportVM(vmID);
		frameAiport = new JFrame();
		frameAiport.setBounds(100, 100, 565, 387);
		frameAiport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAiport.getContentPane().setLayout(null);
		
		JButton btnRentABook = new JButton("Rent Books");
		btnRentABook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RentView rentView=new RentView(vmID);
				rentView.frameRent.setVisible(true);
			}
		});
		btnRentABook.setBounds(402, 88, 117, 29);
		frameAiport.getContentPane().add(btnRentABook);
		
		JLabel lblAirportVirtualMachine = new JLabel("Airport Vending Machine");
		lblAirportVirtualMachine.setForeground(Color.RED);
		lblAirportVirtualMachine.setFont(new Font("Lao Sangam MN", Font.BOLD, 18));
		lblAirportVirtualMachine.setBounds(168, 26, 191, 29);
		frameAiport.getContentPane().add(lblAirportVirtualMachine);
		
		JLabel imgLabel = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/aiport.jpg")).getImage();
		imgLabel.setIcon(new ImageIcon(img));
		
		imgLabel.setBounds(36, 67, 325, 273);
		frameAiport.getContentPane().add(imgLabel);
		
		JButton btnGetVendingCard = new JButton("Get Vending Card");
		btnGetVendingCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewVendingCardView cardView=new NewVendingCardView(airportVM);
				cardView.vendingcardFrame.setVisible(true);
			}
		});
		btnGetVendingCard.setBounds(391, 162, 153, 29);
		frameAiport.getContentPane().add(btnGetVendingCard);
		
		JButton btnNewButton = new JButton("View/Recharge Card");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechargeCardView rView=new RechargeCardView(airportVM);
				rView.frameBalance.setVisible(true);
			}
		});
		btnNewButton.setBounds(391, 243, 153, 29);
		frameAiport.getContentPane().add(btnNewButton);
	}

}
