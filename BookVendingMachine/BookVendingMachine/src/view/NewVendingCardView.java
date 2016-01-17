package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import model.VendingCard;
import model.VendingMachine;

public class NewVendingCardView {

	public JFrame vendingcardFrame;
	private JTextField txtName;
	private JTextField textField;
	private JTextField txtEnterValue;
	private JLabel lblContact;
	private JTextField txtPin;
	private JLabel lblNewLabel;

	
	/**
	 * Create the application.
	 */
	public NewVendingCardView(VendingMachine vm) {
		initialize(vm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final VendingMachine vm) {
		vendingcardFrame = new JFrame();
		vendingcardFrame.setBounds(100, 100, 580, 469);
		vendingcardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vendingcardFrame.getContentPane().setLayout(null);
		
		JLabel lblNewCard = new JLabel("Vending Machine Card");
		lblNewCard.setBounds(166, 34, 181, 16);
		vendingcardFrame.getContentPane().add(lblNewCard);
		
		JLabel lblEnterName = new JLabel("Name");
		lblEnterName.setBounds(37, 158, 85, 16);
		vendingcardFrame.getContentPane().add(lblEnterName);
		
		txtName = new JTextField();
		txtName.setBounds(231, 152, 134, 28);
		vendingcardFrame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(37, 205, 61, 16);
		vendingcardFrame.getContentPane().add(lblAddress);
		
		textField = new JTextField();
		textField.setBounds(231, 199, 134, 28);
		vendingcardFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewCard = new JButton("Submit");
		btnNewCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendingCard vendingcard=new VendingCard();
				vendingcard.setContactNum(txtEnterValue.getText());
				vendingcard.setUserlocation(textField.getText());
				vendingcard.setVedingCard_username(txtName.getText());
				vendingcard.setPin(Integer.parseInt(txtPin.getText()));
				vm.getVendingCard(vendingcard);
			
				JOptionPane.showMessageDialog(null,"Take Your Card");
				vendingcardFrame.dispose();
			}
		});
		btnNewCard.setBounds(230, 381, 117, 29);
		vendingcardFrame.getContentPane().add(btnNewCard);
		
		 
	
		 lblContact = new JLabel("Contact Number");
		 lblContact.setEnabled(true);
		 lblContact.setVisible(true);
		lblContact.setBounds(37, 256, 158, 16);
		vendingcardFrame.getContentPane().add(lblContact);
		
		txtEnterValue = new JTextField();
		txtEnterValue.setEnabled(true);
		txtEnterValue.setVisible(true);
		txtEnterValue.setBounds(231, 250, 134, 28);
		vendingcardFrame.getContentPane().add(txtEnterValue);
		txtEnterValue.setColumns(10);
		
		JLabel lblPin = new JLabel("Pin Number");
		lblPin.setBounds(33, 321, 134, 16);
		vendingcardFrame.getContentPane().add(lblPin);
		
		txtPin = new JTextField();
		txtPin.setBounds(231, 315, 134, 28);
		vendingcardFrame.getContentPane().add(txtPin);
		txtPin.setColumns(10);
		
		lblNewLabel = new JLabel("Enter details to get new vending card:");
		lblNewLabel.setBounds(49, 87, 316, 16);
		vendingcardFrame.getContentPane().add(lblNewLabel);
	}
}
