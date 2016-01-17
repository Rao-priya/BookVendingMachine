package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;

import model.SchoolVM;
import db.SqlDB;
import model.VendingMachine;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

/**This window displays vending machines available so that user can select and access
 * @author Venkata Kambalapalli,Priyanka Rao
 *
 */
public class UserView {

	public JFrame useFrame;
	JButton buttons[] = new JButton[10];

	/**
	 * Create the application.
	 */
	public UserView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		useFrame = new JFrame();
		useFrame.setBounds(100, 100, 450, 300);
		useFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		useFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User View");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setBounds(163, 19, 112, 29);
		useFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Vending Machine");
		lblNewLabel_1.setBounds(22, 60, 169, 16);
		useFrame.getContentPane().add(lblNewLabel_1);
		
		

		SqlDB sqldb = new SqlDB();
		ArrayList<VendingMachine> vmList = sqldb.getVendingMachinesDetails();

		int i = 0, j = 0;
		Iterator<VendingMachine> it=vmList.iterator();
		while(it.hasNext()){
			final VendingMachine vm=it.next();

			buttons[i] = new JButton(vm.getVmName());

			buttons[i].setBounds(29 + j,108, 117, 29);

			useFrame.getContentPane().add(buttons[i]);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					useFrame.dispose();
					if (vm instanceof SchoolVM) {
						SchoolView schoolView = new SchoolView(vm.getVmID());

						schoolView.frameSchoolView.setVisible(true);
						 
						
					} else {
						AirportView airportView = new AirportView(vm.getVmID());
						airportView.frameAiport.setVisible(true);

					}

				}
			});
			j = j + 200;
			i++;
		
		}


	}

}
