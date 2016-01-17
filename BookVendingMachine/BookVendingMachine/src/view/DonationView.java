package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import model.VendingMachine;
import model.SchoolVM;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Font;

public class DonationView {

	public JFrame frameDonation;
	private JTextField textField_bookNo;
	private String navigation;
	
	private JTextField textBookName2;
	JPanel paneldonateBook;
	String no_Book;
	String bookCategoryValues[]={"Grad","UnderGrad","Generic"};
	private JTextField txtBookName[] = new JTextField[4];
	JComboBox bookCategory[]=new JComboBox[5] ;
	//stores book title & category given by user
	List<String> bookcategory_recievd =new ArrayList<>();
	List<String> bookName = new ArrayList<>();
	
	

	/**
	 * Create the application.
	 */
	public DonationView(VendingMachine schoolVM,String navigation) {
		initialize(schoolVM,navigation);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final VendingMachine schoolVM,final String navigation) {
		frameDonation = new JFrame();
		frameDonation.setBounds(150, 150, 550, 400);
		frameDonation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDonation.getContentPane().setLayout(null);
		//No of text book to donate
		textField_bookNo = new JTextField();
		
		textField_bookNo.setBounds(361, 92, 61, 19);
		frameDonation.getContentPane().add(textField_bookNo);
		textField_bookNo.setColumns(10);		
		no_Book=textField_bookNo.getText();
		
		JLabel lblHowManyBooks = new JLabel("How many Books you want to donate?");
		lblHowManyBooks.setBounds(58, 89, 283, 25);
		frameDonation.getContentPane().add(lblHowManyBooks);
		
		JButton btnDonate = new JButton("");
		if("admin".equals(navigation)){
			btnDonate.setText("Add Item");
		}else{
			btnDonate.setText("Donate");
		}
		
		btnDonate.setBounds(205, 322, 117, 25);
		frameDonation.getContentPane().add(btnDonate);
		
		
		paneldonateBook = new JPanel();
		paneldonateBook.setBounds(68, 123, 416, 153);
		frameDonation.getContentPane().add(paneldonateBook);
		paneldonateBook.setLayout(null);
		
		JLabel TextbookName = new JLabel("TextBook");
		TextbookName.setBounds(29, 12, 86, 15);
		paneldonateBook.add(TextbookName);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setBounds(240, 12, 70, 15);
		paneldonateBook.add(lblNewLabel_1);
		paneldonateBook.setVisible(false);
		
				
		JLabel lblDonation = new JLabel("Donation ");
		lblDonation.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblDonation.setBounds(232, 32, 131, 19);
		lblDonation.setVisible(false);
		frameDonation.getContentPane().add(lblDonation);
		if("admin".equals(navigation)){
			JLabel lblNewLabel = new JLabel("Add Books");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
			lblNewLabel.setBounds(219, 33, 112, 16);
			frameDonation.getContentPane().add(lblNewLabel);
		}else{
			lblDonation.setVisible(true);
		}
		
		
		textField_bookNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				paneldonateBook.setVisible(true);
				int j=0;
			   
				//iterate for #bookno and get bookname and book category form user
				for(int i=0;i <Integer.parseInt(textField_bookNo.getText());i++){
					txtBookName[i] = new JTextField(15);					
					txtBookName[i].setBounds(12, 29+j, 114, 19);
					paneldonateBook.add(txtBookName[i]);
					txtBookName[i].setColumns(10);
					//txtBookName[i].setText("");
					txtBookName[i].setVisible(true);
					//JOptionPane.showMessageDialog(null,txtBookName[i].getText());
					
					
					bookCategory[i] = new JComboBox(bookCategoryValues);
					bookCategory[i].setBounds(228, 26+j, 84, 24);
					paneldonateBook.add(bookCategory[i]);
					bookCategory[i].setVisible(true);
					
				
					
					j+=50;
				}
			}
			
		});
		
		
		
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
				for(int i=0;i <Integer.parseInt(textField_bookNo.getText());i++){
					bookName.add(txtBookName[i].getText());
					bookcategory_recievd.add(bookCategory[i].getSelectedItem().toString());
					System.out.println(txtBookName[i].getText());
					System.out.println(bookCategory[i].getSelectedItem().toString());
					
				}
				schoolVM.donateBooks(bookName,bookcategory_recievd);
				if("Exchange".equals(navigation)){
					schoolVM.setItemCount(Integer.parseInt(textField_bookNo.getText()));
					ExchangeView exchangeView=new ExchangeView(schoolVM,null);
					exchangeView.frameExchange.setVisible(true);
					exchangeView.lblexc.setVisible(false);
					exchangeView.textCount.setVisible(false);
					frameDonation.dispose();
					
				}else if("admin".equals(navigation)){
				JOptionPane.showMessageDialog(null,"Items added");
				frameDonation.dispose();
				}else{
					JOptionPane.showMessageDialog(null,"Thank you for your donation");
					frameDonation.dispose();
				}
			}
		});
	}

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
}

