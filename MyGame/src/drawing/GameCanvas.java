package drawing;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Boss;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;
import javafx.scene.input.KeyEvent;

public class GameCanvas extends Canvas {
	
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	GraphicsContext gc = this.getGraphicsContext2D();
	private boolean spawn;

	public GameCanvas() {
		this.setWidth(SceneManager.SCENE_WIDTH);
		this.setHeight(SceneManager.SCENE_HEIGHT);
		this.setVisible(true);
		addListerner();
	}

	public void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});

	}

	public void paintComponent() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, getWidth(), getHeight());
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			// System.out.println(entity.getZ());
			//System.out.println(RenderableHolder.getInstance().getEntities().size());
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}

	}
	
//	public void phase1() {
//		//Fill your code
//		RenderableHolder.getInstance().getBoss() = new Boss(this.getWidth()/2,this.getHeight()/2);
//		phase1=true;
//		Thread t = new Thread(new Runnable() {
//			public void run() {
//				try {
//					long lastLoopStartTime = System.nanoTime();
//					while(true) {
//						long elapsedTime = System.nanoTime() - lastLoopStartTime;
//						Thread.sleep(10);
//						updateBoss1(elapsedTime);
//					}
//					
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
//		t.start();
//	}
//	
//	private void updateBoss1(long now) {
//		double wordX = (1 + Math.sin(now * 1e-9)) * (0.5 * (SceneManager.SCENE_WIDTH));
//		double wordY = 0.25 * (SceneManager.SCENE_HEIGHT);
//		
//		GraphicsContext gc = this.getGraphicsContext2D();
//		RenderableHolder.getInstance().getBoss().get;
//		gc.drawImage(Boss.getImage(), wordX, wordY);
//	}
	
	

}
