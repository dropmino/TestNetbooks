package logic.bean;

/**
 * Bean per la migrazione delle info su recensioni e valutazioni
 * fra layer di controller e quello di view
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BookEvaluationBean {
	
	private String title;
	private String body;
	private int rate;
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
