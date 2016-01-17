package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection implements DBConnection {
	 private static Connection connection = null;
	    private final static String ADRESS   = "jdbc:mysql://localhost";
	    private final static String DATABASE = "vendingdatabase";
	    private final static String USER     = "root";
	    private final static String PASSWORD = "";
	    private final static String PORT     = "3306";
	    private final static String DRIVER   = "com.mysql.jdbc.Driver";
	    private static MySqlConnection mysqlConnection;
	    
	    
	    private MySqlConnection(){
	    	
	    }
	    
	    public  Connection createConnection(){

	    	 try {
		 		  Class.forName(DRIVER).newInstance();
		            connection = DriverManager.getConnection(getFormatedUrl(), USER, PASSWORD);
		        }
		        catch (Exception e) {
		        	
		            errorHandler("Failed to connect to the database " + getFormatedUrl(), e);         
		        }	
	    	 return connection;
	    
	    }
	    public static MySqlConnection getConnection(){
	 	  if(mysqlConnection==null){
	 		 mysqlConnection=new MySqlConnection();
	 	  }
	 	   return mysqlConnection;
	    }
	
	
    
    /**
     * Method that shows the errors thrown by the singleton
     * 
     * @param  {String}    Message
     * @option {Exception} e
     * @return  void
     **/
    
    private static void errorHandler(String message, Exception e) {
        System.out.println(message);  
        if (e != null) System.out.println(e.getMessage());   
    }

    /**
     * Method that returns the formated URL to connect to the database
     * 
     * @return {String}
     **/
    
   private static String getFormatedUrl() {
        return ADRESS + ":" + PORT + "/" + DATABASE;
    }
    

}
