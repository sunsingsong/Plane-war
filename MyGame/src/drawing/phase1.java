 package drawing;

import game.GameMain;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.Boss;
import window.SceneManager;

public class phase1 implements Runnable{
	private GameCanvas canvas;
	private static final long LOOP_TIME = 1000000000;
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	public phase1(GameCanvas canvas) {
		this.canvas=canvas;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		long lastLoopStartTime = System.nanoTime();
		while (true) {
			long elapsedTime = System.nanoTime() - lastLoopStartTime;
			if (elapsedTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateBoss(elapsedTime);
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateBoss(long now) {
		double wordX = (1 + Math.sin(now * 1e-9)) * (0.5 * (SceneManager.SCENE_WIDTH));
		double wordY = 0.25 * (SceneManager.SCENE_HEIGHT);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//gc.drawImage(Boss.getImage(), wordX, wordY);
	}
}
