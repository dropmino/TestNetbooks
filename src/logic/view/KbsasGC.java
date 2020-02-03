package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Line;




public class KbsasGC implements Initializable {
	
	
	 @FXML
	 private ResourceBundle resources;

	 @FXML
	 private URL location;
	  
	 
	 @FXML
	 private Button buttonV, buttonVBS;
	 
	 @FXML
	 private Label labelRes,str1,str2;

	 @FXML
	 private Slider slider;
	 
	 @FXML Line linea;

	 //private KbsasController controller;
	
	@FXML
	public void prova() {
		
		labelRes.setVisible(true);
		buttonV.setVisible(true);
		linea.setVisible(true);
		str1.setVisible(true);
		str2.setVisible(true);
		
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here */
		
	}
	
}
