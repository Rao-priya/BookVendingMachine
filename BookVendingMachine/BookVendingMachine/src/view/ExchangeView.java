package view;



import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;



import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import model.SchoolVM;
import model.Book;
import model.Item;
import model.VendingMachine;

import java.awt.Font;

public class ExchangeView {

	public JFrame frameExchange;
	JPanel panel;
	JButton btnAdd,btnExit;
	JComboBox comboBox[]=new JComboBox[5];
	
	List<Item> bookList;
	public JLabel lblexc;
	public JTextField textCount;
	private JComboBox comboBox_1;
	

	/**
	 * Create the application.
	 * @param schoolVM 
	 * @param string 
	 * @param vmID 
	 */
	public ExchangeView(VendingMachine schoolVM, String navigation) {
		initialize(schoolVM,navigation);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final VendingMachine schoolVM,final String navigation) {
		final ArrayList<String> itemList =new ArrayList<String>();
		frameExchange = new JFrame();
		frameExchange.setBounds(100, 100, 608, 448);
		frameExchange.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameExchange.getContentPane().setLayout(null);
		
		JLabel lblBookVendingMachine = new JLabel("Get Exchange Books");
		lblBookVendingMachine.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblBookVendingMachine.setBounds(196, 21, 202, 25);
		frameExchange.getContentPane().add(lblBookVendingMachine);
		panel = new JPanel();
		panel.setBounds(62, 118, 452, 254);
		frameExchange.getContentPane().add(panel);
		panel.setLayout(null);
		
		//textField_bookDescription = new JTextField();
		//textField_bookDescription.setBounds(28, 12, 216, 36);
		//panel.add(textField_bookDescription);
		//textField_bookDescription.setColumns(10);
		//Create drop-down list; add list of books retrieved form DB into comboBox for display
		int x=0;
		bookList=schoolVM.display_item();
		for(int i=0;i<schoolVM.getItemCount();i++){
			comboBox[i] = new JComboBox();
			//list of books from DB
			for(Iterator<Item> b = bookList.iterator(); b.hasNext();) {
				Item book=b.next();
				book.getItemName();				
				comboBox[i].addItem(book.getItemName().toString());					
			}
			comboBox[i].setBounds(186, 1+x, 159, 27);
			panel.add(comboBox[i]);	
			x+=60;
			
		}
		
		
			
			btnAdd = new JButton("Get Book");
			
			btnAdd.setBounds(57, 211, 117, 25);
			panel.add(btnAdd);
			
			btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frameExchange.dispose();
				}
			});
			
			btnExit.setBounds(302, 211, 117, 25);
			panel.add(btnExit);
			
			JLabel lblSearch = new JLabel("Select items");
			lblSearch.setBounds(17, 6, 117, 15);
			panel.add(lblSearch);
			
			
			
			
			
			
			
			lblexc = new JLabel("Enter item count to remove");
			lblexc.setBounds(23, 83, 208, 16);
			frameExchange.getContentPane().add(lblexc);
			
			textCount = new JTextField();
			textCount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					schoolVM.setItemCount(Integer.parseInt(textCount.getText()));	
					int x=0;
					for(int i=0;i<schoolVM.getItemCount();i++){
						comboBox[i] = new JComboBox();
						//list of books from DB
						for(Iterator<Item> b = bookList.iterator(); b.hasNext();) {
							Item book=b.next();
							book.getItemName();				
							comboBox[i].addItem(book.getItemName().toString());					
						}
						comboBox[i].setBounds(186, 1+x, 159, 27);
						panel.add(comboBox[i]);	
						x+=60;
						
						
						
					}
					panel.repaint();
					panel.validate();
				
				}
				
			});
			textCount.setBounds(331, 71, 134, 28);
			frameExchange.getContentPane().add(textCount);
			textCount.setColumns(10);
			
	
			
	
		
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int bookId = 0;
				
					//JOptionPane.showMessageDialog(null,"Get your "+ comboBox.getSelectedItem().toString()+" Book. \nThank you!");
					//now delete record from DB
					for(int i=0;i<schoolVM.getItemCount();i++){
						itemList.add(comboBox[i].getSelectedItem().toString());
					
						}
					
						for(Iterator<Item> b = bookList.iterator(); b.hasNext();) {
							Item book=b.next();
							Iterator<String> itr = itemList.iterator();
							while(itr.hasNext())
							{
								if(itr.next().equals(book.getItemName())){
							
									bookId=book.getItemId();
									schoolVM.remove_item(bookId);
									System.out.println(bookId);
								
							}
						
						}
						
				}
						if("admin".equals(navigation)){
							JOptionPane.showMessageDialog(null,"Items removed successfully");	
						}else{
							JOptionPane.showMessageDialog(null,"Exchange successful");	
						}
						
						frameExchange.dispose();
						
				}
			});
		
		
	}
}

