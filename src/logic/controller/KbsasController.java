package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.util.enumeration.ImageSizes;
import logic.bean.BookBean;
import logic.model.Book;
import logic.model.users.Retailer;

/**
 * Controller del caso d'uso "known best sellers around shop"
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class KbsasController {
	
	//deve interrogare il Reatailer 

	public List<BookBean> getBooksForRetailer(int radius) {
		
		
		
		Retailer ret = new Retailer("Test", "Retailer");
		
		List<Book> books = ret.getBookFromPosition(radius);
		
		ArrayList<BookBean> beans = new ArrayList<>();	
		
		for (Book b : books) {
			BookBean bean = new BookBean(b.getTitle(), b.getAuthor());
			bean.setSingleImage(b.getSmallImage(), ImageSizes.SMALL);
		
			beans.add(bean);	
		}
		
		return beans;	
	}

}
