package game;

import drawing.GameCanvas;
import javafx.animation.AnimationTimer;
import logic.GameLogic;
import window.SceneManager;


public class GameMain {
	
	private static GameCanvas canvas;
	private static GameLogic logic;
	
	public static void newGame() {
		// TODO fill code
		canvas = new GameCanvas();
		logic = new GameLogic(canvas);
		
		SceneManager.gotoSceneOf(canvas);
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				logic.logicUpdate();
				//canvas.paintComponent();
			}
		};
		animation.start();
		
	}
}
