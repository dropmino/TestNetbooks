package logic.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.model.users.Retailer;
import logic.util.enumeration.ImageSizes;

/**
 * Controller del caso d'uso "known best sellers around shop"
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class KbsasController {
	
	public Map<BookBean , Integer> getBooksForRetailer(int radius) throws PersistencyException {
		
		
		Retailer ret = new Retailer("user");
		ret.setLatitude(41.85545800000001);//si imposterà con reg
		ret.setLongitude(12.6228887);// si imposterà con reg
		
		
		Map<Book , Integer> books = BookDao.findBookForChart(ret.getLatitude(), ret.getLongitude(), radius);
		
		Map<BookBean , Integer> mapBeans = new HashMap<>();	

		for (Map.Entry<Book, Integer> entry : books.entrySet()) {
			BookBean bean = new BookBean(entry.getKey().getTitle(), entry.getKey().getAuthor());
			bean.setSingleImage(entry.getKey().getSmallImage(), ImageSizes.SMALL);
			mapBeans.put(bean, entry.getValue());	 
		}
		
		
		return mapBeans.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
