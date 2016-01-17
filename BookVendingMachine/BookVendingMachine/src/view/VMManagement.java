package view;


import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
















import model.SchoolVM;
import model.VendingMachine;

import controller.VMTypeFactory;
import db.SqlDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;

public class VMManagement {

	public JFrame frame;
	JPanel vMPanel;
	JPanel vMDeletePanel;
	private JTextField textVMName;
	private JTextField textVMLoc;
	 VendingMachine vmachine;
 private String[] vmTypes={"School","Airport"};



	/**
	 * Create the application.
	 */
	public VMManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final SqlDB sqldb = new SqlDB();
		final ArrayList<VendingMachine> vmList = sqldb.getVendingMachinesDetails();
		frame = new JFrame();
		frame.setBounds(100, 100, 595, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblCheckTheStatistics = new JLabel("Check the statistics ");
		lblCheckTheStatistics.setBounds(40, 77, 155, 16);
		frame.getContentPane().add(lblCheckTheStatistics);
		JButton btnClickHere = new JButton("Get VM statistics");
		btnClickHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BarChart b=new BarChart();
				b.barFrame.setVisible(true);
			
			}
		});
		btnClickHere.setBounds(322, 72, 170, 29);
		frame.getContentPane().add(btnClickHere);
		
		
		JLabel lblWelcomeAdmin = new JLabel("Vending Machines Management");
		lblWelcomeAdmin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblWelcomeAdmin.setBounds(158, 22, 304, 29);
		frame.getContentPane().add(lblWelcomeAdmin);
		
		JLabel lblEnterDetailsTo = new JLabel("Select operation");
		lblEnterDetailsTo.setBounds(40, 155, 123, 16);
		frame.getContentPane().add(lblEnterDetailsTo);
		
		JRadioButton rdbtnAddVM = new JRadioButton("Add VM");
		rdbtnAddVM.setBounds(257, 148, 141, 23);
		frame.getContentPane().add(rdbtnAddVM);
		
		JRadioButton rdbtnDeleteVM = new JRadioButton("Delete VM");
		rdbtnDeleteVM.setBounds(437, 148, 141, 23);
		frame.getContentPane().add(rdbtnDeleteVM);
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnDeleteVM);
		bg.add(rdbtnAddVM);
		
		 vMPanel = new JPanel();
		vMPanel.setBounds(29, 205, 549, 194);
		frame.getContentPane().add(vMPanel);
		vMPanel.setLayout(null);
		
		JLabel lblEnterVendingMachine = new JLabel("Enter Vending Machine Name");
		lblEnterVendingMachine.setBounds(29, 33, 215, 16);
		vMPanel.add(lblEnterVendingMachine);
		
		JLabel lblEnterVmInstallation = new JLabel("Enter VM installation Location");
		lblEnterVmInstallation.setBounds(29, 73, 188, 16);
		vMPanel.add(lblEnterVmInstallation);
		
		JLabel lblEnterTypeOf = new JLabel("Select VM Type");
		lblEnterTypeOf.setBounds(29, 119, 188, 16);
		vMPanel.add(lblEnterTypeOf);
		
		JButton btnCreate = new JButton("Setup VM");
		
		btnCreate.setBounds(209, 159, 117, 29);
		vMPanel.add(btnCreate);
		
		textVMName = new JTextField();
		textVMName.setBounds(325, 27, 134, 28);
		vMPanel.add(textVMName);
		textVMName.setColumns(10);
		
		textVMLoc = new JTextField();
		textVMLoc.setBounds(325, 67, 134, 28);
		vMPanel.add(textVMLoc);
		textVMLoc.setColumns(10);
		
		final JComboBox comboBoxVMType = new JComboBox(vmTypes);
		comboBoxVMType.setBounds(325, 115, 98, 27);
		vMPanel.add(comboBoxVMType);
		
		
		 vMDeletePanel = new JPanel();
		 vMDeletePanel.setBounds(40, 205, 549, 194);
		 vMDeletePanel.setLayout(null);
		 vMPanel.setVisible(false);
			vMDeletePanel.setVisible(false);
			frame.getContentPane().add(vMDeletePanel);
		 
		 JLabel lblDeleteVM = new JLabel("Select VM to Delete");
		 lblDeleteVM.setBounds(29, 33, 215, 16);
		 vMDeletePanel.add(lblDeleteVM);
		 JButton btnDelete= new JButton("Delete VM");
		 
btnDelete.setBounds(209, 159, 117, 29);
vMDeletePanel.add(btnDelete);

final JComboBox comboBoxDelete = new JComboBox();

btnDelete.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		String vmName=comboBoxDelete.getSelectedItem().toString();
		Iterator<VendingMachine> it=vmList.iterator();
		while(it.hasNext()){
			final VendingMachine vm=it.next();
			if(vm.getVmName().equals(vmName)){
				vmachine=new SchoolVM(vm.getVmID());
				
			}
			
		}
		JOptionPane.showMessageDialog(null, vmachine.removeVendingMachine());
	}
});
comboBoxDelete.setBounds(272, 27, 134, 28);

vMDeletePanel.add(comboBoxDelete);
		
		JButton btnNewButton = new JButton("Go back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminView aView=new AdminView();
				aView.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		Iterator<VendingMachine> it=vmList.iterator();
		while(it.hasNext()){
			final VendingMachine vm=it.next();
			comboBoxDelete.addItem(vm.getVmName());
		} 
		rdbtnAddVM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMPanel.setVisible(true);
				vMDeletePanel.setVisible(false);
			}
		});
		rdbtnDeleteVM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vMDeletePanel.setVisible(true);
				vMPanel.setVisible(false);
			}
		});
	
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VMTypeFactory vmFactory=new VMTypeFactory();
				boolean status=vmFactory.createVM(textVMName.getText(),textVMLoc.getText(),comboBoxVMType.getSelectedItem().toString());
				if(status){
					JOptionPane.showMessageDialog(null, "Vending Machine Setup success!!");
					frame.dispose();
					VMManagement window = new VMManagement();
					window.frame.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(null, "Vending Machine Setup Failed");
				}
			
				
			}
		});
	
		
		
	}
}
