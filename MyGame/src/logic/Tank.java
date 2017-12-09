package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Tank extends CollidableEntity {
	private int tick=0;
	private int lastTick=0;
	public int direction =0;
	public boolean fire = false;

	private static final int speed = 4;
	private int angle = 0; // angle 0 = EAST
	private boolean flashing = false;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;

	public Tank(int x, int y) {
		this.width=40;
		this.height=30;
		this.x = x;
		this.y = y;
		this.z=1;
		this.radius = 8;
		this.setImage(new Image("res/player_front.png"));
	}

	private void forward() {
		this.y+=(this.y<=0)?0:-1*speed;
	}
	
	private void backward() {
		this.y+=(this.y>=SceneManager.SCENE_HEIGHT-30)?0:speed;
	}
	
	private void turnleft() {
		this.x+=(this.x<=0)?0:-1*speed;
	}
	
	private void turnright() {
		this.x+=(this.x>=SceneManager.SCENE_WIDTH-40)?0:speed;
	}
 

//	public void hitByMine() {
//		flashing = true;
//		flashCounter = 10;
//		flashDurationCounter = 10;
//	}

	public void update() {
		for(IRenderable i:RenderableHolder.getInstance().getEntities()) {
			if(this.collideWith((CollidableEntity)i)){
				if((i instanceof Bullet)&&(((Bullet)i).isEnemy)){	
					((Bullet)i).destroyed=true;
					this.destroyed=true;
				}
				if(i instanceof Boss) {
					this.destroyed=true;	
				}
				if(i instanceof Bomb) {
					this.destroyed=true;
				}
				if(i instanceof Laser) {
					this.destroyed=true;
				}
				if(i instanceof Enemy) {
					this.destroyed=true;
				}
			}
		}
		this.fire = false;
		if (flashing) {
			if (flashCounter == 0) {
				this.visible = true;
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					this.visible = flashCounter <= 5;
					flashDurationCounter--;
				} else {
					this.visible = true;
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		} else {
			this.visible = !InputUtility.getKeyPressed(KeyCode.SHIFT);
		}
		if (InputUtility.getKeyPressed(KeyCode.LEFT)) {
			turnleft();
			this.direction=2;
			this.setImage(new Image("res/player_left.png"));
		}
		if (InputUtility.getKeyPressed(KeyCode.RIGHT)) {
			turnright();
			this.direction=3;
			this.setImage(new Image("res/player_right.png"));
		}
		if (InputUtility.getKeyPressed(KeyCode.UP)) {
			forward();
			this.direction=0;
			this.setImage(new Image("res/player_front.png"));
		}
		if (InputUtility.getKeyPressed(KeyCode.DOWN)) {
			backward();
			this.direction=1;
			this.setImage(new Image("res/player_down.png"));
		}
		
		if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
			if(tick>=lastTick) {
				tick=lastTick;
				this.fire = true;
				fire();
				lastTick+=50;
			}
			//tick++;
		}
		tick++;
		
//		if (InputUtility.isLeftClickTriggered()) {
//			this.x = InputUtility.mouseX;
//			this.y = InputUtility.mouseY;
//		}
	}
	
	public void fire() {
//		Bullet aBullet = new Bullet(x,y,direction);
//		RenderableHolder.getInstance().getEntities().add(aBullet);
	}

	@Override
	public void draw(GraphicsContext gc) {
//		gc.setFill(Color.BLUE);
//		gc.fillArc(x - radius, y - radius, radius * 2, radius * 2, 0, 360, ArcType.OPEN);
//		gc.translate(x, y);
//		gc.rotate(angle);
//		gc.setFill(Color.YELLOW);
//
//		int gunSize = radius / 5;
//		gc.fillRect(0, -gunSize, radius * 3 / 2, gunSize * 2);
//		gc.rotate(-angle);
//		gc.translate(-x, -y);
		gc.setGlobalAlpha(1);
		gc.drawImage(image, x, y);
	}

}
