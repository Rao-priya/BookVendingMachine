package view;

import java.awt.EventQueue;

import javax.swing.*;

import model.VendingCard;
import controller.CashPaymentStrategy;
import controller.PaymentStrategy;
import controller.VendingCardStrategy;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PaymentView {

	public JFrame frame;
	private JTextField cashValue;
	JPanel panelPayment;
	JPanel panelCard;
	private JTextField textCard;
	private JTextField textPin;
	JComboBox comboBox1;
	JComboBox comboBox2;
	JComboBox comboBox5;
	JComboBox comboBox10;
	
	private String cardNumber;

	PaymentStrategy paymentstrategy;
	JRadioButton rdbtnVendingCard;
	

	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Create the application.
	 */
	public PaymentView(int amount) {
		initialize(amount);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int amount) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblpayType = new JLabel("choose your payment type");
		lblpayType.setBounds(16, 44, 219, 16);
		frame.getContentPane().add(lblpayType);
		
		JRadioButton rdbtnCash = new JRadioButton("Cash");
		
		rdbtnCash.setBounds(196, 40, 63, 23);
		frame.getContentPane().add(rdbtnCash);
		
		
		
		
		 panelPayment = new JPanel();
		panelPayment.setBounds(29, 99, 395, 152);
		frame.getContentPane().add(panelPayment);
		panelPayment.setLayout(null);
		panelPayment.setVisible(false);
		//JLabel labelCash = new JLabel("Enter Cash value");
		//labelCash.setBounds(25, 6, 136, 16);
		//panelPayment.add(labelCash);
		
		//cashValue = new JTextField();
		//cashValue.setBounds(181, 0, 134, 28);
		//panelPayment.add(cashValue);
		//cashValue.setColumns(10);
		
		JButton btnSubmitCash = new JButton("Submit");
	
		btnSubmitCash.setBounds(127, 106, 117, 29);
		panelPayment.add(btnSubmitCash);
		
		 comboBox1 = new JComboBox();
		comboBox1.setBounds(76, 34, 67, 27);
		panelPayment.add(comboBox1);
		
		
		
		 comboBox5 = new JComboBox();
		comboBox5.setBounds(76, 73, 67, 27);
		panelPayment.add(comboBox5);
		
		 comboBox2 = new JComboBox();
		comboBox2.setBounds(312, 34, 67, 27);
		panelPayment.add(comboBox2);
		
		 comboBox10 = new JComboBox();
		comboBox10.setBounds(312, 74, 67, 27);
		panelPayment.add(comboBox10);
		for(int i=0;i<15;i++){
			comboBox1.addItem(i);
			comboBox2.addItem(i);
			comboBox10.addItem(i);
			comboBox5.addItem(i);
		}
		
		JLabel lblDollar_1 = new JLabel("1 Dollar");
		lblDollar_1.setBounds(6, 38, 61, 16);
		panelPayment.add(lblDollar_1);
		
		JLabel lblDollar_2 = new JLabel("2 Dollar");
		lblDollar_2.setBounds(218, 40, 61, 16);
		panelPayment.add(lblDollar_2);
		
		JLabel lblDollar_5 = new JLabel("5 Dollar");
		lblDollar_5.setBounds(6, 77, 61, 16);
		panelPayment.add(lblDollar_5);
		
		JLabel lblDollar_10 = new JLabel("10 Dollar");
		lblDollar_10.setBounds(218, 78, 61, 16);
		panelPayment.add(lblDollar_10);
		
		btnSubmitCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
				paymentstrategy=new CashPaymentStrategy();
				((CashPaymentStrategy)paymentstrategy).setOneDollar((int) comboBox1.getSelectedItem());
				((CashPaymentStrategy)paymentstrategy).setTwoDollar((int)comboBox2.getSelectedItem());
				((CashPaymentStrategy)paymentstrategy).setFiveDollar((int)comboBox5.getSelectedItem());
				((CashPaymentStrategy)paymentstrategy).setTenDollar((int)comboBox10.getSelectedItem());
			
				
				JOptionPane.showMessageDialog(null, paymentstrategy.pay(amount));
				frame.dispose();
			
			
			}
		});
		
		
		 panelCard = new JPanel();
		panelCard.setBounds(29, 99, 395, 158);
		frame.getContentPane().add(panelCard);
		panelCard.setVisible(false);
		panelCard.setLayout(null);
		
		JLabel labelCard = new JLabel("Enter Card Number");
		labelCard.setBounds(25, 31, 136, 16);
		panelCard.add(labelCard);
		
		textCard = new JTextField();
		textCard.setBounds(178, 25, 134, 28);
		panelCard.add(textCard);
		textCard.setColumns(10);
		
		JLabel lblPin = new JLabel("Enter Pin Number");
		lblPin.setBounds(25, 71, 153, 16);
		panelCard.add(lblPin);
		
		textPin = new JTextField();
		textPin.setBounds(178, 65, 134, 28);
		panelCard.add(textPin);
		textPin.setColumns(10);
		JButton btnSubmitCard = new JButton("Submit");
		btnSubmitCard.setBounds(127, 106, 117, 29);
		panelCard.add(btnSubmitCard);
		
		 rdbtnVendingCard = new JRadioButton("Vending Card");
			rdbtnVendingCard.setBounds(309, 40, 116, 23);
			frame.getContentPane().add(rdbtnVendingCard);
			
			ButtonGroup buttonGroup = new ButtonGroup();
			buttonGroup.add(rdbtnCash);
			buttonGroup.add(rdbtnVendingCard);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(16, 16, 428, 16);
			frame.getContentPane().add(lblNewLabel);
		
			lblNewLabel.setText("You need to pay $:"+amount);
		
		btnSubmitCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendingCard vc=new VendingCard();
				vc.setPin(Integer.parseInt(textPin.getText()));
				vc.setVendingCard_id(Integer.parseInt(textCard.getText()));
				paymentstrategy=new VendingCardStrategy(vc);
				
				JOptionPane.showMessageDialog(null, paymentstrategy.pay(amount));
				frame.dispose();
				
			}
		});
		rdbtnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPayment.setVisible(true);
				panelCard.setVisible(false);
			}
		});
		rdbtnVendingCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCard.setVisible(true);
				panelPayment.setVisible(false);
				
			}
		});
	}
}
