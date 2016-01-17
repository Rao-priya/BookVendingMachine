package controller;

import db.SqlDB;
import model.VendingCard;



public class VendingCardStrategy implements PaymentStrategy{
	VendingCard vc;
	public VendingCardStrategy(VendingCard vc){
		this.vc=vc;
	}
	
	@Override
	public String pay(int amount) {
		SqlDB sqldb=new SqlDB();
		String message=sqldb.selectVendingCardDB(vc,amount);
		return message;
	}

}
