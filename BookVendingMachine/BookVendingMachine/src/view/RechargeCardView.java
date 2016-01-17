package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import model.VendingCard;
import model.VendingMachine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RechargeCardView {

	public JFrame frameBalance;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblEnterYourCard ;
	JLabel lblNewLabel;
	JButton btnNewButton;
	JButton btnRecharge;
	private JLabel lblNewLabel_1;
	private int amountInserted;
	VendingCard vendingCard=new VendingCard();
	private int cardId;
	private JPanel panel;
	private JButton btnNewButton_1;
	private JLabel lblVendingCard;
	
	
	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getAmountInserted() {
		return amountInserted;
	}

	public void setAmountInserted(int amountInserted) {
		this.amountInserted = amountInserted;
	}

	/**
	 * Create the application.
	 */
	public RechargeCardView(VendingMachine vm) {
		initialize(vm);
	}
	public void rechargingUsingCash(){
		vendingCard.rechargeCard(getAmountInserted());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final VendingMachine vm) {
		
		frameBalance = new JFrame();
		frameBalance.setBounds(100, 100, 563, 391);
		frameBalance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameBalance.getContentPane().setLayout(null);
		
		 lblEnterYourCard = new JLabel("Enter Card Number");
		lblEnterYourCard.setBounds(28, 86, 159, 16);
		frameBalance.getContentPane().add(lblEnterYourCard);
		
		 lblNewLabel = new JLabel("Enter Pin ");
		lblNewLabel.setBounds(28, 163, 139, 16);
		frameBalance.getContentPane().add(lblNewLabel);
		
		 btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(199, 247, 117, 29);
		frameBalance.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(209, 80, 134, 28);
		frameBalance.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(209, 157, 124, 28);
		frameBalance.getContentPane().add(passwordField);
		
		 btnRecharge = new JButton("Recharge");
	
		btnRecharge.setBounds(77, 247, 117, 29);
		btnRecharge.setVisible(false);
		frameBalance.getContentPane().add(btnRecharge);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
		lblNewLabel_1.setBounds(77, 114, 424, 37);
		lblNewLabel_1.setVisible(false);
		frameBalance.getContentPane().add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameBalance.dispose();
			}
		});
		btnNewButton_1.setBounds(363, 247, 117, 29);
		frameBalance.getContentPane().add(btnNewButton_1);
		
		lblVendingCard = new JLabel("Vending Card");
		lblVendingCard.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblVendingCard.setBounds(220, 17, 210, 29);
		frameBalance.getContentPane().add(lblVendingCard);
		
		
		 btnRecharge.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		lblNewLabel_1.setVisible(false);
			 		btnRecharge.setVisible(false);
			 		CashPaymentView cashPayment=new CashPaymentView(0,getCardId());
					cashPayment.setBounds(77, 59, 424, 243);
					cashPayment.setLayout(null);
			 		frameBalance.getContentPane().add(cashPayment);
			 	
			 	}
			 });
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vendingCard.setVendingCard_id(Integer.parseInt(textField.getText()));
				vendingCard.setPin(Integer.parseInt(passwordField.getText()));
				setCardId(Integer.parseInt(textField.getText()));
				
				int status=vm.validateCard(vendingCard);
				if(status==-1){
					JOptionPane.showMessageDialog(null,"Validation failed. Retry again");
				}else{
					textField.setVisible(false);
					passwordField.setVisible(false);
					lblEnterYourCard.setVisible(false);
					lblNewLabel.setVisible(false);
					btnRecharge.setVisible(true);
					lblNewLabel_1.setVisible(true);
					btnNewButton.setVisible(false);
					lblNewLabel_1.setText("Balance in your Card:  $"+status);
					
					
					
				}
			}
		});
	}
}
