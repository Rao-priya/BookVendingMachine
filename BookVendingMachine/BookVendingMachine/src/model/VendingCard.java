package model;

import db.SqlDB;

public class VendingCard {
	
	int vendingCard_id;
	String vedingCard_username;
	String userlocation;
	String contactNum;
	int pin;
	int amount;
	SqlDB sqldb=new SqlDB();
	

	public String rechargeCard(int amount){
		int balanceOnCard=sqldb.validateCardDB(this);
		
		return sqldb.updateVendingCardDB(getVendingCard_id(),balanceOnCard+amount);
		
	}
	
	
	//add amount
	public void useCard(){
		
	}//deduct amount
	public int getVendingCard_id() {
		return vendingCard_id;
	}
	public void setVendingCard_id(int vendingCard_id) {
		this.vendingCard_id = vendingCard_id;
	}
	public String getVedingCard_username() {
		return vedingCard_username;
	}
	public void setVedingCard_username(String vedingCard_username) {
		this.vedingCard_username = vedingCard_username;
	}

	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUserlocation() {
		return userlocation;
	}
	public void setUserlocation(String userlocation) {
		this.userlocation = userlocation;
	}
	
	
}
