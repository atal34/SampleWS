package service;

import beans.Employee;
import dao.DatabaseManager;

public class ServiceManager {

	
public String createTpinService(Employee e){
		
		DatabaseManager dbm = new DatabaseManager();
		String msg = dbm.createNewTpin(e);
		
		return msg;
	}
	
	public String modifyTpinService(Employee e){
		
		DatabaseManager dbm = new DatabaseManager();
		String msg = dbm.modifyTpinTpin(e);
		
		return msg;
	}
}
