package view;

import javax.swing.*;

import model.VendingCard;
import controller.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashPaymentView extends JPanel {
	JComboBox comboBox1;
	JComboBox comboBox2;
	JComboBox comboBox5;
	JComboBox comboBox10;
	JButton btnSubmitCash;
	PaymentStrategy paymentstrategy;

	/**
	 * Create the panel.
	 */
	public CashPaymentView(final int amountToPay,final int cardID) {
		 setLayout(null);
		
		 comboBox1 = new JComboBox();
		comboBox1.setBounds(112, 40, 64, 27);
		add(comboBox1);
		
		
		
		 comboBox5 = new JComboBox();
		comboBox5.setBounds(305, 40, 64, 27);
		add(comboBox5);
		
		 comboBox2 = new JComboBox();
		comboBox2.setBounds(112, 99, 64, 27);
		add(comboBox2);
		
		 comboBox10 = new JComboBox();
		comboBox10.setBounds(317, 99, 52, 27);
		add(comboBox10);
		for(int i=0;i<15;i++){
			comboBox1.addItem(i);
			comboBox2.addItem(i);
			comboBox10.addItem(i);
			comboBox5.addItem(i);
		}
		
		JLabel lblDollar_1 = new JLabel("1 Dollar");
		lblDollar_1.setBounds(6, 44, 50, 16);
		add(lblDollar_1);
		
		JLabel lblDollar_2 = new JLabel("2 Dollar");
		lblDollar_2.setBounds(6, 103, 50, 16);
		add(lblDollar_2);
		
		JLabel lblDollar_5 = new JLabel("5 Dollar");
		lblDollar_5.setBounds(210, 44, 50, 16);
		add(lblDollar_5);
		
		 btnSubmitCash = new JButton("Submit");
		 
	
		 btnSubmitCash.setBounds(144, 163, 88, 29);
		 add(btnSubmitCash);
		 btnSubmitCash.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if(cardID!=0){
			 		VendingCard vc=new VendingCard();
			 		vc.setVendingCard_id(cardID);
			 		int amount=1*(int) comboBox1.getSelectedItem()+2*(int) comboBox2.getSelectedItem()+5*(int) comboBox5.getSelectedItem()+10*(int) comboBox10.getSelectedItem();
			 		if("success".equals(vc.rechargeCard(amount))){
			 			JOptionPane.showMessageDialog(null, "Your card is recharged with $"+amount );
			 		}
			 		
			 		
			 		}else{

					paymentstrategy=new CashPaymentStrategy();
					((CashPaymentStrategy)paymentstrategy).setOneDollar((int) comboBox1.getSelectedItem());
					((CashPaymentStrategy)paymentstrategy).setTwoDollar((int)comboBox2.getSelectedItem());
					((CashPaymentStrategy)paymentstrategy).setFiveDollar((int)comboBox5.getSelectedItem());
					((CashPaymentStrategy)paymentstrategy).setTenDollar((int)comboBox10.getSelectedItem());
				
					
					JOptionPane.showMessageDialog(null, paymentstrategy.pay(amountToPay));
					
					
				
			 		}
			 	}
			 });
		
		JLabel lblDollar_10 = new JLabel("10 Dollar");
		lblDollar_10.setBounds(210, 103, 58, 16);
		add(lblDollar_10);
	
	}

}
