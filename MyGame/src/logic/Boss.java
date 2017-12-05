package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Boss extends CollidableEntity {
	public int direction = 4;
	private int hp = 15;
	private int speed = 3;
	private int tick = 0;
	private int lastTick = 0;
	boolean spawn = true;
	boolean b1;
	boolean flashing=false;
	int flashCounter = 10;
	int flashDurationCounter = 10;

	public Boss(int x, int y) {
		this.radius = 26;
		this.width = 96;
		this.height = 96;
		this.x = x;
		this.y = y;
		this.z = 2;
		this.setImage(new Image("res/boss1.png"));
	}

	public void update() {
		b1 = false;
		if (hp <= 10) {
			this.speed = 5;
		}
		if (hp <= 6) {
			this.speed = 8;
		}
		if (hp <= 3) {
			this.speed = 9;
		}
		// System.out.println(this.hp);
		for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if (this.collideWith((CollidableEntity) i)) {
				if (i instanceof Bullet && !((Bullet) i).isEnemy&&!flashing) {
					((Bullet) i).destroyed = true;
					this.hp -= ((Bullet) i).getDamage();
					this.flashing=true;
				}
			}
		}
		flashState();
		if (hp > 0) {
			if (this.direction == 4) {
				this.x -= speed;
				if (x <= 0) {
					this.direction = 1;
				}
			}
			if (this.direction == 1) {
				this.x += speed;
				if (this.x >= SceneManager.SCENE_WIDTH - this.width) {
					this.direction = 2;
				}
			}
			if (this.direction == 2) {
				this.y += speed;
				if (this.y >= SceneManager.SCENE_HEIGHT - this.height) {
					this.direction = 0;
				}
			}
			if (this.direction == 0) {
				this.x -= speed;
				if (this.x <= 0) {
					this.direction = 3;
				}
			}
			if (this.direction == 3) {
				this.y -= speed;
				if (this.y <= 0) {
					this.direction = 1;
				}
			}
			if (tick >= lastTick) {
				tick = lastTick;
				lastTick += 50;
			}
			if (tick % ((10 - this.speed) * 6) == 0) {
				b1 = true;
				//fire(this.direction);
			}
		}
		tick++;
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(this.image, this.x, this.y);
	}
	
	public void flashState() {
		if (flashing) {
			if (flashCounter == 0) {
				flashDurationCounter = 10;
				flashCounter=10;
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
		}
	}


}
