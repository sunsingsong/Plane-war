package game;

import drawing.GameCanvas;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
		Thread game = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					if (!logic.isEnd) {
						Platform.runLater(new Runnable() {
							public void run() {
								logic.logicUpdate();
							}
							
						});
					}
					else {
						canvas.clearComponent();
						break; 
					}
					if(logic.isTerminate)
						break;
					try {
						Thread.sleep(12);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}) ;
		game.start();
		/*AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				if(!logic.isEnd)
					logic.logicUpdate();
				//canvas.paintComponent();
			}
		};
		animation.start();*/
		
	}
}
