package controller;

public class OverStockState extends VendingMachineState{

	@Override
	public void notifyStockStatus(int vmID) {
		System.out.println(" Remove Items from :"+vmID);
		
	}

}
