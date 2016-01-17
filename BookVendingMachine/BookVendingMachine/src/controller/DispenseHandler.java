package controller;

import java.util.ArrayList;

import model.Currency;

public interface DispenseHandler {
	void setNextChain(DispenseHandler nextChain);
    
	ArrayList<String> dispense(Currency cur,ArrayList<String> msg);
}
