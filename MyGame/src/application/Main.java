
package application;

import drawing.GameCanvas;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoMainMenu();
			primaryStage.setTitle("Plane War");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		StackPane root = new StackPane();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.setTitle("Tank game");
//
//		GameLogic logic = new GameLogic();
//		GameScreen gameScreen = new GameScreen(768, 576);
//		root.getChildren().add(gameScreen);
//		gameScreen.requestFocus();
//		
//		stage.show();
//		
//		AnimationTimer animation = new AnimationTimer() {
//			public void handle(long now) {
//				gameScreen.paintComponent();
//				logic.logicUpdate();
//				RenderableHolder.getInstance().update();
//			}
//		};
//		animation.start();
	}
}