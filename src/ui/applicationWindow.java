package ui;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

/**
 * javafx tutorial used here https://www.youtube.com/watch?v=t4ehYIynI34&vl=en
 * download eclipse fx plugin from https://www.eclipse.org/efxclipse/install.html
 * download javafx scenebuilder from https://gluonhq.com/products/scene-builder/
 * 
 * @author ccarn
 *
 */
public class applicationWindow extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button ("Click me");
		Button exit = new Button("exit");
		
		exit.setOnAction(e -> {
			System.out.println("Exiting..");
			System.exit(0);
			
		});
		btn.setOnAction(e -> System.out.println("Testing"));
		//Vbox (vertical box) and stackpanes are different layouts we can use for javafx
		VBox root = new VBox();		
		//StackPane root = new StackPane();
		root.getChildren().addAll(btn, exit);
		// set the scene default size
		Scene scene = new Scene(root,500,500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MyTitle");
		primaryStage.show();
	}
	
	
	
}
