package view;


import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

















import model.SchoolVM;
import model.VendingMachine;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;










import controller.VMTypeFactory;
import db.SqlDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AdminView {

	public JFrame frame;
	 VendingMachine vmachine;
 private String[] vmTypes={"School","Airport"};

	

	/**
	 * Create the application.
	 */
	public AdminView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 595, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome Admin!!");
		lblWelcomeAdmin.setBounds(242, 19, 143, 16);
		frame.getContentPane().add(lblWelcomeAdmin);
		ButtonGroup bg=new ButtonGroup();
		
		
		

		
		
		JButton btnNewButton = new JButton("Items management");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemManagement itemManagement=new ItemManagement();
				itemManagement.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(87, 341, 155, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Vending Machines Management");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VMManagement vmmgnmt = new VMManagement();
				vmmgnmt.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(329, 341, 235, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(108, 50, 387, 279);
		Image img=new ImageIcon(this.getClass().getResource("/admin.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainView mView=new MainView();
				mView.frame.setVisible(true);
			}
		});
		btnLogOut.setBounds(472, 14, 117, 29);
		frame.getContentPane().add(btnLogOut);
	
		
		
	}
}
