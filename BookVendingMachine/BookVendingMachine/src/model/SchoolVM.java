package model;

import java.util.List;

import db.SqlDB;
import model.Book;
import model.Item;
import model.VendingMachine;

public class SchoolVM extends VendingMachine {
	int vmID;
	SqlDB sqlDB=new SqlDB();
	
	public SchoolVM(){
		
	}
	public SchoolVM(int vmId) {
		this.vmID=vmId;
	}

	
	public List<Item> display_item() {
		return sqlDB.getBooks(vmID);
		
		 
		
	}

	
	public void remove_item(int bookId) {
		sqlDB.removeBooks(bookId,vmID);
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
	public void donateBooks(List<String> bookname, List<String> category){
		Book[] book=new Book[bookname.size()];
		for(int i=0;i<bookname.size();i++){
			book[i]=new Book();
			book[i].setItemCategory(category.get(i));
			book[i].setItemName(bookname.get(i));	
			
			
	}
		SqlDB sqldb=new SqlDB();
		sqldb.donateBook_insert(book,vmID);
		super.vmID=vmID;
		super.changeState();
	}
	
	public String removeVendingMachine(){
		 return sqlDB.deleteVM(vmID);
		 }
	

}
