package window;

import game.EndGame;
import game.WinnerGame;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.MainMenu;


public final class SceneManager {

	private static Stage primaryStage;
	private static Canvas mainMenuCanvas = new MainMenu();
	private static EndGame endGame = new EndGame();
	private static WinnerGame winnerGame = new WinnerGame();
	private static Scene mainMenuScene = new Scene(new Pane(mainMenuCanvas));
	private static Scene endGameScene = new Scene(new Pane(endGame));
	private static Scene winnerGameScene = new Scene(new Pane(winnerGame));
	public static final int SCENE_WIDTH = 800;
	public static final int SCENE_HEIGHT = 600;

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoMainMenu() {
		//TODO Fill Code
		mainMenuCanvas.requestFocus();
		primaryStage.setScene(mainMenuScene);
	}
	public static void gotoEndGame() {
		endGame.requestFocus();
		primaryStage.setScene(endGameScene);
	}
	public static void gotoWinnerGame() {
		winnerGame.requestFocus();
		primaryStage.setScene(winnerGameScene);
	}
	public static void gotoSceneOf(Canvas canvas) {
		//TODO Fill Code
		Scene s = new Scene(new Pane(canvas));
		canvas.requestFocus();
		primaryStage.setScene(s);
	}
}