package logic.model.users;

import java.util.List;

import logic.dao.BookDao;
import logic.model.Book;

/**
 * 
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class Retailer extends User {
	
	public Retailer(String username, String email) {
		super(username, email);		
	}
	
	public Retailer(String username) {
		super(username);
	}	
	
	//metodo che interroga la BookDao
	public List<Book> getBookFromPosition(int radius) {
			
		return BookDao.getInstance().findBookForChart(latitude, longitude , radius);
		
	}
	
}