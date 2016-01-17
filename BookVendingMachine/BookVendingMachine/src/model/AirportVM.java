package model;

import java.util.List;
import java.util.Map;

import db.SqlDB;


public class AirportVM extends VendingMachine {
	
	
	SqlDB sqlDB=new SqlDB();
	public AirportVM(){
		
	}
	public AirportVM(int vmID) {
		this.vmID=vmID;
		// TODO Auto-generated constructor stub
	}

	
	public List<Item> display_item() {
		return sqlDB.getBooks(vmID);
	}

	
	public void remove_item(int id) {
		sqlDB.removeBooks(id,vmID);
		super.vmID=vmID;
		super.changeState();
		
	}

	
	public void payfor_item() {
		// TODO Auto-generated method stub
		
	}

	
	public void get_VendingMachineDetails() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean createVendingMachine() {
		// TODO Auto-generated method stub
		return false;
	}

	public int calculateRent(Map<Integer, String> map) {
		return sqlDB.calculateRent(map,vmID);
		
	}
	@Override
	public void donateBooks(List<String> bookname, List<String> category) {
		// TODO Auto-generated method stub
		
	}
	public String removeVendingMachine(){
		 return sqlDB.deleteVM(vmID);
		 }

}
