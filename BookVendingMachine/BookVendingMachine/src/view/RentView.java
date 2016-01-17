package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.AirportVM;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.*;

import javax.swing.JButton;
import java.awt.Font;

public class RentView {

	public JFrame frameRent;
	List<Item> bookList;
	JComboBox combo[]=new JComboBox[5] ;
	 int countRent=0;



	/**
	 * Create the application.
	 */
	public RentView(int vmID) {
		initialize(vmID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final int vmID) {
		final AirportVM airportVM=new AirportVM(vmID);
	
		frameRent = new JFrame();
		frameRent.setBounds(100, 100, 594, 463);
		frameRent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRent.getContentPane().setLayout(null);
		
		JLabel lblHowManyBooks = new JLabel("How many books you want to rent?");
		lblHowManyBooks.setBounds(36, 79, 225, 16);
		frameRent.getContentPane().add(lblHowManyBooks);
		
		final JComboBox comboRent = new JComboBox();
		
		comboRent.setBounds(335, 75, 71, 27);
		frameRent.getContentPane().add(comboRent);
		for(int i=0;i<5;i++){
			comboRent.addItem(i);
		}
		
		
		final JPanel panelRent = new JPanel();
		panelRent.setBounds(34, 129, 538, 280);
		frameRent.getContentPane().add(panelRent);
		panelRent.setLayout(null);
		
		JButton btnNewButton = new JButton("Rent");
		
		btnNewButton.setBounds(118, 245, 117, 29);
		panelRent.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameRent.dispose();
				
				AirportView aView=new AirportView(vmID);
				aView.frameAiport.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(357, 245, 117, 29);
		panelRent.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Rent Book");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setBounds(238, 21, 116, 16);
		frameRent.getContentPane().add(lblNewLabel);
		panelRent.setVisible(false);
		
		
		comboRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRent.setVisible(true);
				 countRent=(int) comboRent.getSelectedItem();
				int j=0;
				for(int i=0;i<countRent;i++){
					
					combo[i] = new JComboBox();
					combo[i].setBounds(40, 6+j, 185, 27);
				
					combo[i].setVisible(true);
					bookList=airportVM.display_item();//list of books from DB
					for(Iterator<Item> b = bookList.iterator(); b.hasNext();) {
						Item book=b.next();
						book.getItemName();				
						combo[i].addItem(book.getItemName().toString());
					}	
					panelRent.add(combo[i]);
					j+=50;
				}
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<Integer,String> map=new HashMap<Integer,String>();
				bookList=airportVM.display_item();
				
				int bookId = 0;
				for(int i=0;i<countRent;i++){
				for(Iterator<Item> b = bookList.iterator(); b.hasNext();) {
					Item book=b.next();
					book.getItemName();
				if(book.getItemName().equals(combo[i].getSelectedItem().toString())){
					bookId=book.getItemId();
					airportVM.remove_item(bookId);
					break;
				}//string
				
		}
				map.put(bookId, combo[i].getSelectedItem().toString());
				}
				
				frameRent.dispose();
				PaymentView paymentView=new PaymentView(airportVM.calculateRent(map));
				paymentView.frame.setVisible(true);
				
			}
		});
		
		
	}
}
