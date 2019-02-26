package beans;

public class Employee {
	
	String ID;
	int tpin;
	
	public Employee(String iD, int tpin) {
		super();
		ID = iD;
		this.tpin = tpin;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getTpin() {
		return tpin;
	}
	public void setTpin(int tpin) {
		this.tpin = tpin;
	}
	
	

}
