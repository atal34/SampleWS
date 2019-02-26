package main;

import helper.Constants;
import helper.HashingMD5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import beans.Employee;

@Path("/webservice")
public class TestWSClass {
	@GET
    @Path("/createTPIN/empID/{employeeid}/tpin/{tpin}")
    @Produces("text/plain")
    public String createTpin(@PathParam("employeeid")String employeeId, @PathParam("tpin")String tpin){
		String sql = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
		    Statement stmt = null;
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres","postgres", "Password-1");
			
			connection.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        HashingMD5 hash = new HashingMD5();
	        tpin = hash.getHashPin(tpin, employeeId.concat(Constants.SECRETKEY).getBytes());
	        
	        stmt = connection.createStatement();
	        sql = "INSERT INTO public.tpin(emp_id, pin) "
	            + "VALUES ('"+employeeId+"','"+tpin+"')";
	         int dbResult = stmt.executeUpdate(sql);
	         stmt.close();
	         connection.commit();
	         connection.close();
	        
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
			return "TPIN creation failed ....";
		} 
		
        return "TPIN creation Successful ...."+sql;    
    }
	
	@GET
    @Path("/validateTPIN/empID/{employeeid}/tpin/{tpin}")
    @Produces("text/plain")
    public String validateTpin(@PathParam("employeeid")String employeeId, @PathParam("tpin")String tpin){
		
		HashingMD5 hash = new HashingMD5();
		String sql = "SELECT emp_id,pin from public.tpin where emp_id = '"+employeeId+"'";
		String result = "TPIN validation Successful ....";
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
		    Statement stmt = null;
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres","postgres", "Password-1");
			
			 stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
		            String dbId = rs.getString("emp_id");
		            String  dbPin = rs.getString("pin");
		            String enteredPin = hash.getHashPin(tpin, dbId.concat(Constants.SECRETKEY).getBytes());
		            if (dbPin.equals(enteredPin)){
		            	result = "validation successfully";
		            	//return result;
		            }
		            else{
		            	result = "validation fail";
		            	//return result;
		            }
		            
			} 
			else{
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "exception occured"+e;
		} 
	
		return result;
	}
	
	@GET
    @Path("/employee/{employeeid}")
	@Produces("text/plain")
    public String getEmployee(@PathParam("employeeid")String employeeId){
		return "web service called successfully ....";    
    }

}
