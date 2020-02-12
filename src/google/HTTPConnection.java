package google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HTTPConnection{
	
	private static HttpURLConnection connection;
	
	
	public static void main(String[] args) {
	
	BufferedReader reader;
	String line;
	StringBuffer responseContent = new StringBuffer();
	
	try {
		URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?query=16+della+Quercia+00030&key=AIzaSyBl14kach2u1grko-cm1LhsE7OnJWow8pw");
		connection = (HttpURLConnection) url.openConnection();
		
		//Request setup
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
		
		int status = connection.getResponseCode();	//if status > 299 somethings went wrong

		if(status > 299) {
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			while((line = reader.readLine()) != null) {
				responseContent.append(line);
			}
			reader.close();
		} else {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				
				responseContent.append(line);
			}
			reader.close();
		}
		System.out.println(responseContent.toString());
		
//		parse(responseContent.toString());
		
			
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		
	}
}