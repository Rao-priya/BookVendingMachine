package controller;

public class OutofStockState extends VendingMachineState {

	@Override
	public void notifyStockStatus(int vmID) {
		System.out.println(" Insert Items in :"+vmID);
		
	}

}
