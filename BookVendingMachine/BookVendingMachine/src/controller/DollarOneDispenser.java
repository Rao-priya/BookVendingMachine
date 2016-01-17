package controller;

import java.util.ArrayList;

import model.Currency;

public class DollarOneDispenser implements DispenseHandler {
	  private DispenseHandler chain;
	     
	    @Override
	    public void setNextChain(DispenseHandler nextChain) {
	        this.chain=nextChain;
	    }
	 
	    @Override
	    public ArrayList<String> dispense(Currency cur,ArrayList<String> msg) {
	    	String s=null;
	        if(cur.getAmount() >= 1){
	            int num = cur.getAmount()/1;
	            int remainder = cur.getAmount() % 1;
	            s="Dispensing "+num+" 1$ note";
	            msg.add(s);
	            if(remainder !=0) this.chain.dispense(new Currency(remainder),msg);
	        }else{
	            this.chain.dispense(cur,msg);
	        }
	        return msg;
	    }
}
