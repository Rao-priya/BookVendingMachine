package controller;

import java.util.ArrayList;

import model.Currency;
import db.SqlDB;

public class CashPaymentStrategy implements PaymentStrategy{
	private int oneDollar;
	private int twoDollar;
	private int fiveDollar;
	private int tenDollar;


	@Override
	public String pay(int amount) {
		
		int amountInserted=1*getOneDollar()+2*getTwoDollar()+5*getFiveDollar()+10*getTenDollar();
		int difference=amountInserted-amount;
		if(difference==0){
			return "Thank you for your Payment";
		}else if(difference>0){
			StringBuffer sBuffer=new StringBuffer();
			ArrayList<String> msg= dispenseChange(difference);
			for(int i=0;i<msg.size();i++){
				sBuffer.append(msg.get(i));
				sBuffer.append("\n");
			}
			return sBuffer.toString();
		}else{
			return "Please insert $"+(-1*difference)+" more";
		}
	
		
	}
	
	private ArrayList<String>  dispenseChange(int amount) {
		ArrayList<String> msg=new ArrayList<String>();
		DispenseHandler c1=new DollarFiveDispenser();
		DispenseHandler c2=new DollarTwoDispenser();
		DispenseHandler c3=new DollarOneDispenser();
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		return (c1.dispense(new Currency(amount),msg));
		
	}

	
		public int getOneDollar() {
			return oneDollar;
		}
	
		public void setOneDollar(int oneDollar) {
			this.oneDollar = oneDollar;
		}
	
		public int getTwoDollar() {
			return twoDollar;
		}
	
		public void setTwoDollar(int twoDollar) {
			this.twoDollar = twoDollar;
		}
	
		public int getFiveDollar() {
			return fiveDollar;
		}
	
		public void setFiveDollar(int fiveDollar) {
			this.fiveDollar = fiveDollar;
		}
	
		public int getTenDollar() {
			return tenDollar;
		}
	
		public void setTenDollar(int tenDollar) {
			this.tenDollar = tenDollar;
		}
	
	

}
