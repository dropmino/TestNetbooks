package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.ImageDispenser;
import logic.util.enumeration.BookGenres;
import logic.util.enumeration.ImageSizes;

/**
 * Versione singleton del DAO per l'interazione
 * con lo strato di persistenza per l'entity {@link Book}
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0252795)
 * @author Alessandro Calomino (M. 0258841)
 */
public class BookDao {
	
	private static Book book1 = new Book("000001", "La Divina Commedia", "Dante");
	private static Book book2 = new Book("000002", "The Great Gatsby", "F. Fitzgerhald");
	
	private BookDao() {
		/* non instanziabile */
	}
	
	private static Book buildBookFromResultSet(ResultSet res) throws SQLException {
		String isbn = res.getString("isbn");
		String title = res.getString("title");
		String author = res.getString("author");
		
		Book book = new Book(isbn, title, author);
		book.setAmazonLink(res.getString("link_amz"));
		book.setLanguage(res.getString("language"));
		book.setLargeImage(ImageDispenser.getCovers(book.getTitle(), ImageSizes.LARGE));
		book.setMediumImage(ImageDispenser.getCovers(book.getTitle(), ImageSizes.MEDIUM));
		book.setMondadoriLink(res.getString("link_mnd"));
		book.setPlayLink(res.getString("link_play"));
		book.setPublisher(res.getString("publisher"));
		book.setSmallImage(ImageDispenser.getCovers(book.getTitle(), ImageSizes.SMALL));
		book.setYearOfPublication(res.getInt("year"));
		
		return book;
		
	}
	
	public static List<Book> findBooksForHomepage(String user) throws PersistencyException  {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<Book> books = new ArrayList<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_BOOKS_FOR_HP_SP);
			results = DBOperation.bindParameters(stmt, user);
			
			while (results.next()) {
				Book tmp = BookDao.buildBookFromResultSet(results);
				books.add(tmp);	
			}
			 
			return books;
			
		} catch(ClassNotFoundException | SQLException e) {
			throw new PersistencyException("Unable to load books for homepage");
		}
		finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static List<Book> findExchangeableBooks(String username) {
		List<Book> books = new ArrayList<>();
		
		if (username.equals("")) {
			for (int i = 0; i < 9; i ++) {
				book1.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
				book1.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
				books.add(book1);
				book2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
				books.add(book2);
			}
		}
		
		return books;
	}
	
	public BookGenres findBookByGenre(String genre) {
		if (genre.equals("thr"))
			return BookGenres.THRILLER;
		else if (genre.equals("rom"))
			return BookGenres.ROMANCE;
		else return BookGenres.UNDEFINED;
	}
	
	
	public static  Map<Book, Integer> findBookForChart(double latitude, double longitude , int radius) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		int numOfCopySold = 0 ;
		
		try {
			Map<Book, Integer> bookInChart = new HashMap<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_BOOK_FOR_CHART_SP);
			results = DBOperation.bindParametersAndExec(stmt, latitude, longitude, radius);
			
			while (results.next()) {
				Book book = new Book(results.getString("isbn") , results.getString("title") , results.getString("author") );
				numOfCopySold = results.getInt("count(*)");
				bookInChart.put(book, numOfCopySold);
				book.setSmallImage(ImageDispenser.getCovers(book.getTitle(), ImageSizes.SMALL));
			}
			
			return bookInChart;
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistencyException("Unable to load books for homepage");
		} finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);		}
		
	}
		
		
	
}


