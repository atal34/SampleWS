package main;

import helper.Constants;
import helper.HashingMD5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashingMD5 hash = new HashingMD5();
		String sql = "SELECT * from public.tpin where emp_id = '"+610008+"'";
		System.out.println(" sql= "+sql);
		String result = "Ws called";
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
		    Statement stmt = null;
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres","postgres", "Password-1");
			
			 stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 System.out.println();
			 
			 if ( rs.next() ) {
				 System.out.println(rs.getString("emp_id"));
				 System.out.println(rs.getString("pin"));
		            String id = rs.getString("emp_id");
		            String  pin = rs.getString("pin");
		            String enteredPin = "3752";
		            String hashPin = hash.getHashPin(enteredPin, id.concat(Constants.SECRETKEY).getBytes());
		            System.out.println(hash.getHashPin(enteredPin, id.concat(Constants.SECRETKEY).getBytes()));
		            if (pin.equals(hashPin)){
		            	result = "success";
		            }
		            else{
		            	result = "fail";
		            }
		            
		         }
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
