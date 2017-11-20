package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Bullet extends CollidableEntity {
	 private final int BULLET_SPEED = 3;
	    private final int BOARD_WIDTH = 640;
	    private final int BOARD_HEIGHT = 380;
	    private int direction;
	    private boolean upgrade = false;
	    public boolean isEnemy;

	    public Bullet(int x, int y, int direction) {
	        this.x=x;
	        this.y=y;
	        this.direction = direction;
	       /* if (direction == 0) {
	            loadImage("image/bullet_up.png");
	        }
	        if (direction == 1) {
	            loadImage("image/bullet_right.png");
	        }
	        if (direction == 2) {
	            loadImage("image/bullet_down.png");
	        }
	        if (direction == 3) {
	            loadImage("image/bullet_left.png");
	        }*/
	        //isEnemy = Enemy;
	        //getImageDimensions();
	    }
	    public void update() {
	        if (direction == 0) {
	            y -= BULLET_SPEED;
	        } else if (direction == 1) {
	            y += BULLET_SPEED;
	        } else if (direction == 2) {
	            x -= BULLET_SPEED;
	        } else if (direction == 3) {
	             x+= BULLET_SPEED;
	        }
	        if (x > BOARD_WIDTH + 100 + width) {
	        	visible = false;
	        }
	        if (x < -width - 100) {
	        	visible = false;
	        }
	        if (y > BOARD_HEIGHT + height + 100) {
	        	visible = false;
	        }
	        if (y < -height - 100) {
	        	visible = false;
	        }
	    }
	    public void upgrade() {
	        this.upgrade = true;
	    }

	    public boolean getUpgrade() {
	        return this.upgrade;
	    }
		@Override
		public void draw(GraphicsContext gc) {
			// TODO Auto-generated method stub
			image = new Image("res/sun.png");
			gc.drawImage(image, this.x, this.y);
			
			
		}
	    
}
