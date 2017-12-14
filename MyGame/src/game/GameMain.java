package game;

import drawing.GameCanvas;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.GameLogic;
import window.SceneManager;


public class GameMain {
	public static boolean isPress = false;
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
					if (!logic.isEnd()&&!logic.isPause) {
						Platform.runLater(new Runnable() {
							public void run() {
								logic.logicUpdate();
							}
						});
					}
					else if(logic.isEnd()){
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
					addEvent();
				}
				
			}
			
		}) ;
		game.start();
	}
	  public static void addEvent() {
		  if(InputUtility.getKeyPressed(KeyCode.ENTER)){
				if(!isPress) {
					if (logic.isPause) {
						logic.botBackground.play();
						if(!logic.isBossDead())
							logic.bossBackground.play();
						canvas.paintPauseGame(false);
						logic.isPause = false;
					}
					else {
						logic.botBackground.stop();
						logic.bossBackground.stop();
						canvas.paintPauseGame(true);
						logic.isPause = true;
					}
				   isPress=true;
				}
			}
			else  isPress=false;
		  if(InputUtility.getKeyPressed(KeyCode.ESCAPE)) {
			  logic.isTerminate=true;
			  Platform.exit();
		  }
	  }
	
}
