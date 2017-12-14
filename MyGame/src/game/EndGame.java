package game;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import window.SceneManager;

public class EndGame extends Canvas{
	    public  GraphicsContext gc;

		public  EndGame() {
			super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
			gc = this.getGraphicsContext2D();
			gc.fillRect(0, 0,1000, 1000);
			gc.setFill(Color.CORNFLOWERBLUE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.fillText("GAME OVER",SceneManager.SCENE_WIDTH/2, SceneManager.SCENE_HEIGHT/2);
			gc.fillText("Try Again?",SceneManager.SCENE_WIDTH/2, SceneManager.SCENE_HEIGHT/2+200);
			addKeyEventHandler();
			
		}
		private void addKeyEventHandler() {
			//TODO Fill Code
			this.setOnKeyPressed((KeyEvent event)->{
				if(event.getCode()==KeyCode.ESCAPE) {
					Platform.exit();
				}
				if(event.getCode()==KeyCode.ENTER) {
					SceneManager.gotoMainMenu();
				}
			});
		}
}
