package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.AirportVM;
import model.SchoolVM;
import model.Book;
import model.Item;
import model.VendingCard;
import model.VendingMachine;





public class SqlDB {

	Statement stmt;
	
ConnectionFactory cf=new ConnectionFactory();

Connection con=cf.createSqlConnection("mysql");

	public String selectVendingCardDB(VendingCard vc, int amount) {
		
		int bal=validateCardDB(vc);
		String s=null;
		if(bal==-1){
			s = "No card with this number";
		}else{
			try {
				s = deductVC(vc.getVendingCard_id(), amount, bal);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}
	
	

	public String deductVC(int cardNumber, int amount, int bal)
			throws SQLException {
		
			String s=null;
				
				if (bal < amount) {
					s = "Low balance";
				} else {
					int newbalance = bal - amount;
					updateVendingCardDB(cardNumber, newbalance);
					s = "Payment success";
				}
		
		return s;
	}

	public String updateVendingCardDB(int cardNumber, int newbalance) {
		
		try {
			stmt = con.createStatement();
			String query = "update VENDINGCARD SET amount=" + newbalance
					+ " WHERE idVENDINGCARD=" + cardNumber;
			stmt.executeUpdate(query);
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Failed";
	}

	public String insertNewVendingCard(VendingCard vc) {
		try {
			stmt = con.createStatement();
			String query="insert into VENDINGCARD(name,address,contact,pin,amount) values('"+vc.getVedingCard_username()+"','"+vc.getUserlocation()+"','"+vc.getContactNum()+"',"+vc.getPin()+","+0+")";
			
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int validateCardDB(VendingCard vc) {
		String q = "SELECT * FROM VENDINGCARD where idVENDINGCARD=? and pin=?";
		PreparedStatement pst;
		String s = null;
		try {
			pst = con.prepareStatement(q);
			pst.setInt(1, vc.getVendingCard_id());
			pst.setInt(2, vc.getPin());
			ResultSet rs = pst.executeQuery();
			if(!rs.next()){
				return -1;
			}else
				return rs.getInt(6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}

	
	
	


	
	
	
	
	
	public boolean createNewVendingMachine(VendingMachine vm, String type) {
		try {

			stmt = con.createStatement();
			String query = "insert into MASTERVM(LOCATION,TYPE,VMNAME) values('"+ vm.getLocation()+ "','"
					+ type
					+ "','"
					+ vm.getVmName()
					+ "')";
			stmt.executeUpdate(query);
			return getNewVMId();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean getNewVMId() throws SQLException {
		int id = 0;
		String selectQuery = "select idVM from MASTERVM ORDER BY idVM DESC LIMIT 1 ";
		PreparedStatement stmtCreate = con.prepareStatement(selectQuery);
		ResultSet rs = stmtCreate.executeQuery();
		if (rs.next()) {
			id = rs.getInt(1);
			createVMTable(id, stmt);
		} else {
			return false;
		}
		return true;
	}

	private void createVMTable(int id, Statement stmt) throws SQLException {
		String vmNewName = "VENDINGMACHINE" + id;
		String query2 = "Create table "
				+ vmNewName
				+ " (idBook INTEGER not NULL PRIMARY KEY AUTO_INCREMENT,flag VARCHAR(255),title VARCHAR(255),category VARCHAR(255),publication VARCHAR(255),price INTEGER)";
		stmt.executeUpdate(query2);
	}
	
	public ArrayList<VendingMachine> getVendingMachinesDetails() {
		ArrayList<VendingMachine> vmList = new ArrayList<VendingMachine>();
		VendingMachine vm;
		try {

			String selectQuery = "select * from MASTERVM";
			PreparedStatement stmtCreate = con.prepareStatement(selectQuery);
			ResultSet rs = stmtCreate.executeQuery();

			while (rs.next()) {
				if (rs.getString(3).equals("School")) {
					vm = new SchoolVM();

				} else {
					vm = new AirportVM();
				}
				vm.setVmID(rs.getInt(1));
				vm.setVmName(rs.getString(4));

				vmList.add(vm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vmList;
	}
	
	
	
	
	
	
	
	
public List<Item> getBooks(int vmID){
	List<Item> bookList = new ArrayList<>();
	

	
	 try{
	 stmt=con.createStatement();
	 String tableName="VENDINGMACHINE"+vmID;
	 ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+" WHERE flag='add'");
	 while (rs.next()) {
		 Item b=new Book();
	  ((Book) b).setItemId(rs.getInt(1));
	     ((Book) b).setItemName(rs.getString(3));//name
	     ((Book) b).setItemCategory(rs.getString(4));//category
	     bookList.add(b);
	  }
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    return bookList;
 
	
}

	public void removeBooks(int bookId, int vmID) {
		String tableName = "VENDINGMACHINE" + vmID;
		
		
		try {
			stmt = con.createStatement();
			String sql = "UPDATE " + tableName + " SET flag='delete' where idBook=" + bookId+ "";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getItemCount(int id) {
		int count = 0;
		String tableName = "VENDINGMACHINE" + id;
		PreparedStatement statement = null;
		try {

			statement = (PreparedStatement) con.prepareStatement("SELECT count(*) AS rowcount from "+ tableName + " where flag='add'");

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				count = result.getInt("rowcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}



	public void donateBook_insert(Book[] book,int vmID) {
		 String tableName="VENDINGMACHINE"+vmID;
	
	String sql=null;
	try{	
		Statement stmt=con.createStatement();
		
		for(int i=0;i<book.length;i++){
			System.out.println(i);
			System.out.println(book[i].getItemName());
			System.out.println(book[i].getItemCategory());
		sql= "INSERT INTO "+tableName+"(flag,title,category,publication,price) VALUES('add','"+book[i].getItemName()+"', '"+book[i].getItemCategory()+"','',0)";
		
		stmt.execute(sql);
		
		}
	
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	
	public ArrayList<Integer> getBooksAddedForEachMachine(int id) {
		ArrayList<Integer> transactionCount = new ArrayList<Integer>(); 
		PreparedStatement statement = null; 
		 String tableName="VENDINGMACHINE"+id;
		try {
			statement=(PreparedStatement)con.prepareStatement("SELECT count(*) AS rowcount from "+tableName+" where flag='add'");
			//statement = (PreparedStatement) con.prepareStatement("SELECT count(*) AS rowcount from TRANSACTIONTABLE where VENDINGMCID="+i+" and TRANSTYPE='add'");
		
		ResultSet result = statement.executeQuery(); 
		if (result.next()) {
			transactionCount.add(result.getInt("rowcount"));
		}
		statement=(PreparedStatement)con.prepareStatement("SELECT count(*) AS rowcount from "+tableName+" where flag='delete'");
		//statement = (PreparedStatement) con.prepareStatement("SELECT count(*) AS rowcount from TRANSACTIONTABLE where VENDINGMCID="+i+" and TRANSTYPE='delete'");
		ResultSet result2 = statement.executeQuery(); 
		if (result2.next()) {
			transactionCount.add(result2.getInt("rowcount"));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionCount;
	}


	


	public int calculateRent(Map<Integer, String> map, int vmID) {
		String tableName="VENDINGMACHINE"+vmID;
		 int totalPrice=0;
		 try{
			 stmt=con.createStatement();
			 ResultSet rs ;
			
		for (Map.Entry<Integer, String> entry : map.entrySet())
		{
			 int price=0;
				
				  rs = stmt.executeQuery("SELECT * FROM "+tableName+" WHERE idBook="+entry.getKey());
				 while (rs.next()) {
					 price=rs.getInt(6);
				  }
				 totalPrice=totalPrice+price;
				// removeBooks(entry.getKey(), vmID) ;
		} 
		return totalPrice;
		} catch (SQLException e) {
		 	// TODO Auto-generated catch block
		 	e.printStackTrace();
		 }
		return totalPrice;
	}



	public String deleteVM(int vmID) {
		try {
			stmt = con.createStatement();
			String tableName="VENDINGMACHINE"+vmID;
			String sql = "DROP TABLE "+tableName;
			String query = "DELETE FROM MASTERVM WHERE idVM ="+vmID;
			stmt.executeUpdate(sql);
			stmt.executeUpdate(query);
			return " VM removed successfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "Error removing VM";
	}

	
}
