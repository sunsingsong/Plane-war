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
	        } else if (direction == 1||this.direction==4) {
	            y += BULLET_SPEED;
	        } else if (direction == 2) {
	            x -= BULLET_SPEED;
	        } else if (direction == 3) {
	             x+= BULLET_SPEED;
	        }
	        if (x > SceneManager.SCENE_WIDTH + 100 + width) {
	        	visible = false;
	        	destroyed=true;
	        }
	        if (x < -width - 100) {
	        	visible = false;
	        	destroyed=true;
	        }
	        if (y > SceneManager.SCENE_HEIGHT + height + 100) {
	        	visible = false;
	        	destroyed=true;
	        }
	        if (y < height - 100) {
	        	visible = false;
	        	destroyed=true;
	        }
	    }
	    public void upgrade() {
	    	if(this.damage<4) {
	    		 this.damage++;
	    	}
	    }

//	    public boolean getUpgrade() {
//	        return this.upgrade;
//	    }
		@Override
		public void draw(GraphicsContext gc) {
			// TODO Auto-generated method stub
//			image = new Image("res/1.png");
//			gc.drawImage(image, this.x, this.y);
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
