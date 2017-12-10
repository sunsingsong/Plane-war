package logic;

import java.util.Random;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Enemy extends CollidableEntity {
	private int tick = 0;
	private int tick1 = 0;
	private int lastTick = 0;
	private int lastTick1 = 0;
	public int direction = 0;
	public boolean fire = false;
	public int levelEnemy = 1;
	public  boolean changeLevel=true;
	private static final int speed = 3;
	private int angle = 0; // angle 0 = EAST
	private boolean flashing = false;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;

	public Enemy(int x, int y) {
		this.width = 40;
		this.height = 30;
		this.x = x;
		this.y = y;
		this.z = 1;
		this.radius = 32;
		this.setImage(new Image("bot1.png"));
	}

	private void forward() {
		this.y += (this.y <= 0) ? 0 : -1 * speed;
	}

	private void backward() {
		this.y += (this.y >= SceneManager.SCENE_HEIGHT - 30) ? 0 : speed;
	}

	private void turnleft() {
		this.x += (this.x <= 0) ? 0 : -1 * speed;
	}

	private void turnright() {
		this.x += (this.x >= SceneManager.SCENE_WIDTH - 40) ? 0 : speed;
	}

//	public void hitByMine() {
//		flashing = true;
//		flashCounter = 10;
//		flashDurationCounter = 10;
//	}

	public void update() {
		checkEnemyDead();
		this.fire = false;
		setPosition();

		if (tick >= lastTick) {
			tick = lastTick;
			this.fire = true;
			lastTick += 50;
		}
		tick++;
	}
	
	public void checkEnemyDead() {
		for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if (this.collideWith((CollidableEntity) i)) {
				if ((i instanceof Bullet) && !(((Bullet) i).isEnemy)) {
					if(levelEnemy==2) {
						((Bullet) i).destroyed = true;
						this.setImage(new Image("bot1.png"));
						levelEnemy=1;
					}
					else if(levelEnemy==3) {
						((Bullet) i).destroyed = true;
						this.setImage(new Image("bot2.png"));
						levelEnemy=2;
					}
					else {
						((Bullet) i).destroyed = true;
						this.destroyed = true;
					}
				}
			}
		}
	}

	public void setPosition() {
		Random rand = new Random();
		int randDirect = rand.nextInt(4);
		if (tick1 >= lastTick1) {
			if (randDirect == 0) {
				forward();
				this.direction = 0;

			} else if (randDirect == 1) {
				backward();
				this.direction = 1;

			} else if (randDirect == 2) {
				turnleft();
				this.direction = 2;

			} else if (randDirect == 3) {
				turnright();
				this.direction = 3;

			}
			tick1 = lastTick1;
			lastTick1 += 100;
		} else {
			if (this.direction == 0) {
				forward();
			} else if (this.direction == 1) {
				backward();
				if (this.y <= 0) {
					this.direction = 0;

				}
			} else if (this.direction == 2) {
				turnleft();
				if (this.x <= 0) {
					this.direction = 3;

				}
			} else if (this.direction == 3) {
				turnright();
			}
		}
		tick1++;
	}

	// public void fire() {
	// Bullet aBullet = new Bullet(x,y,direction);
	// RenderableHolder.getInstance().getEntities().add(aBullet);
	// }

	@Override
	public void draw(GraphicsContext gc) {
		gc.setGlobalAlpha(1);
		gc.drawImage(image, x, y);
	}

}
