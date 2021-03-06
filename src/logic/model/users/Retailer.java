package logic.model.users;

/**
 * 
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class Retailer extends User {
	
	private String vat;
	private String company;
	
	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Retailer(String username, String email) {
		super(username, email);		
	}
	
	public Retailer(String username) {
		super(username);
	}	

	
}