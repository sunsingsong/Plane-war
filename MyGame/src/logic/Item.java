package logic;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Item extends CollidableEntity{
	private int x;
	private int y;
	public int itemType;
	public int tickItem=0;
	public Item() {
		Random rand= new Random();
		this.x=rand.nextInt(SceneManager.SCENE_WIDTH);
		this.y=rand.nextInt(SceneManager.SCENE_HEIGHT);
		randomItem();
		this.setImage(new Image("res/Armor.png"));
	}
	private void randomItem() {
		Random rand= new Random();
		this.itemType=rand.nextInt(2);
	}
	public void update() {
		this.tickItem++;
		if(this.tickItem>=600) {
			this.destroyed=true;
			this.tickItem=0;
		}
		else {
			for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
				if (this.collideWith((CollidableEntity) i)) {
					if (i instanceof Tank) {
						this.destroyed=true;
						
					}
				}
			}
		}
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(image, x, y);
	}

}
