package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.ImageFactory;
import logic.util.enumeration.ImageSet;
import logic.view.navbar.Navbar;


public class Testkbsas extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(DesktopLauncher.class.getResource("view/resources/fxml/kbsas.fxml"));
		
//		KbsasGC gc  = new KbsasGC();
		
		BorderPane root = loader.load();
		Navbar navbar = new Navbar(primaryStage);
		
		root.setTop(navbar);
		
		Scene scene = new Scene(root);
		
		setupStage(primaryStage, scene);
		primaryStage.show();	
	}
	
	private void setupStage(Stage stage, Scene scene) {
		
		stage.setTitle("Netbooks v1.0");
		stage.setResizable(false);		
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.getIcons().add(ImageFactory.getImage(ImageSet.ICON));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
