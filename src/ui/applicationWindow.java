package ui;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

public class applicationWindow extends Application {

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button ("Click me");
		btn.setOnAction(e -> System.out.println("Testing"));
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
}
