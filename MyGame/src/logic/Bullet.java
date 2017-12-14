  package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import window.SceneManager;

public class Bullet extends CollidableEntity {
	 private final int BULLET_SPEED = 5;
	    private int direction;
	    private int damage;
//	    private boolean upgrade = false;
	    public boolean isEnemy;

	    public Bullet(int x, int y, int direction,boolean isEnemy) {
	    	this.damage=1;
	        this.x=x;
	        this.y=y;
	        this.direction = direction;
	        this.isEnemy=isEnemy;
	    }
	    public void update() {
	    		setDirection();
	    		checkBulletDestroy();
	    }
	    public void upgrade() {
	    	if(this.damage<4) 
	    		 this.damage++;
	    	 }
	    private void setDirection() {
		if (direction == 0) {
			y -= BULLET_SPEED;
		} else if (direction == 1 || this.direction == 4) {
			y += BULLET_SPEED;
		} else if (direction == 2) {
			x -= BULLET_SPEED;
		} else if (direction == 3) {
			x += BULLET_SPEED;
		}
	}

	private void checkBulletDestroy() {
		if (x > SceneManager.SCENE_WIDTH + 100 + width) {
			visible = false;
			destroyed = true;
		}
		if (x < -width - 100) {
			visible = false;
			destroyed = true;
		}
		if (y > SceneManager.SCENE_HEIGHT + height + 100) {
			visible = false;
			destroyed = true;
		}
		if (y < height - 100) {
			visible = false;
			destroyed = true;
		}
	}
		@Override
		public void draw(GraphicsContext gc) {
			gc.setGlobalAlpha(1);
			switch(this.damage) {
			case 1:
				gc.setFill(Color.WHEAT);
				break;
			case 2:
				gc.setFill(Color.YELLOW);
				break;
			case 3:
				gc.setFill(Color.ORANGE);
				break;
			case 4:
				gc.setFill(Color.RED);
				break;
			default:
				gc.setFill(Color.WHITE);
			}
			
			this.height=18;
			this.width=6;
			switch(this.direction) {
			case 2:
				width=18;height=6;
			case 3:
				width=18;height=6;
			}
			gc.fillRect(this.x, this.y, width, height);
				
		}
		
		public int getDamage() {
			return this.damage;
		}
		
		public void otk() {
			this.damage=100;
		}
	    
}
