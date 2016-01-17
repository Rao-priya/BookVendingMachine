package controller;


import db.SqlDB;
import model.AirportVM;
import model.SchoolVM;
import model.VendingMachine;

public class VMTypeFactory {
 SqlDB sqldb=new SqlDB();
	VendingMachine vm;
	public boolean createVM(String vmName, String vmLocation, String vmType){
		VendingMachine vm;
		switch(vmType){
		case "School":
		
			vm=new SchoolVM();
			vm.setVmName(vmName);
			vm.setLocation(vmLocation);
		    return sqldb.createNewVendingMachine(vm, "School");
			
		case "Airport":
			vm=new AirportVM();
			vm.setVmName(vmName);
			vm.setLocation(vmLocation);
			  return sqldb.createNewVendingMachine(vm, "Airport");
		}
		return false;
	}
}
