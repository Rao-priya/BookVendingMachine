package view;

import java.awt.EventQueue;

import javax.swing.*;

import model.SchoolVM;
import db.SqlDB;
import model.VendingMachine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Font;
import java.awt.Color;

public class ItemManagement {

	public JFrame frame;
	public JPanel panelItems;
	
	 VendingMachine vmachine;


	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ItemManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 618, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JComboBox comboBox = new JComboBox();
	
		comboBox.setBounds(383, 140, 109, 27);
		frame.getContentPane().add(comboBox);
		
		 panelItems = new JPanel();
		 panelItems.setBackground(Color.LIGHT_GRAY);
		panelItems.setBounds(120, 185, 340, 150);
		frame.getContentPane().add(panelItems);
		panelItems.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Add Items");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelItems.setVisible(false);
				DonationView dView=new DonationView(vmachine,"admin");
				dView.frameDonation.setVisible(true);
			
				
			}
		});
		btnNewButton.setBounds(6, 58, 117, 29);
		panelItems.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove Items");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExchangeView eView=new ExchangeView(vmachine,"admin");
				eView.frameExchange.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(205, 58, 117, 29);
		panelItems.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Select Vending Machine");
		lblNewLabel.setBounds(43, 144, 207, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item Management");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 21));
		lblNewLabel_1.setBounds(180, 6, 340, 37);
		frame.getContentPane().add(lblNewLabel_1);
		panelItems.setVisible(false);
		
		
		SqlDB sqldb = new SqlDB();
		final ArrayList<VendingMachine> vmList = sqldb.getVendingMachinesDetails();

		int i = 0, j = 0;
		Iterator<VendingMachine> it=vmList.iterator();
		while(it.hasNext()){
			final VendingMachine vm=it.next();
comboBox.addItem(vm.getVmName());
		}   
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vmName=comboBox.getSelectedItem().toString();
				Iterator<VendingMachine> it=vmList.iterator();
				while(it.hasNext()){
					final VendingMachine vm=it.next();
					if(vm.getVmName().equals(vmName)){
						vmachine=new SchoolVM(vm.getVmID());
						
					}
					
				}
				panelItems.setVisible(true);
				
			}
		});
	}
}
