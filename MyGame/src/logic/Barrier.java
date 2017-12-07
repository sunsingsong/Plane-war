package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Barrier extends CollidableEntity {
	
	private Boss boss;
	private int mX;
	private int mY;
	private int tick=-10;
	
	public Barrier(Boss boss) {
		this.boss=boss;
		this.x=(SceneManager.SCENE_WIDTH/2-boss.width/2)-50;
		this.y=(SceneManager.SCENE_HEIGHT/2-boss.height/2)-50;
		this.mX=(SceneManager.SCENE_WIDTH/2-boss.width/2);
		this.mY=(SceneManager.SCENE_HEIGHT/2-boss.height/2);
	}
	
	public void update() {
		if(boss.barrier) {
			for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
				if (this.collideWith((CollidableEntity) i)) {
					if (i instanceof Bullet) {
						((Bullet) i).destroyed = true;
					}
					if(i instanceof Tank) {
						((Tank)i).destroyed=true;	
					}
				}
			}
			if(tick==0) {
				this.destroyed=true;
			}
			tick++;
		}
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(boss.barrier) {
			gc.setGlobalAlpha(0.5);
			gc.setStroke(Color.DEEPSKYBLUE);
			gc.setLineWidth(5);
			//gc.fillOval(x, y, 50, 50);
			gc.strokeRect(x,y, 200, 200);
		}
		
	}
	
}
