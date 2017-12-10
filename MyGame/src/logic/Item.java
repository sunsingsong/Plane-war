package logic;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Item extends CollidableEntity{
	private int x;
	private int y;
	public int itemType;
	public Item() {
		Random rand= new Random();
		this.x=rand.nextInt(SceneManager.SCENE_WIDTH);
		this.y=rand.nextInt(SceneManager.SCENE_HEIGHT);
		randomItem();
	}
	private void randomItem() {
		Random rand= new Random();
		this.itemType=rand.nextInt(2);
	}
	public void update() {
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.fillRect(x, y, 20, 20);
	}

}
