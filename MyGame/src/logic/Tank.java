package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class Tank extends CollidableEntity {
	private int fireTick=0;
	private int lastFireTick=0;
	public int direction =0;
	public boolean fire = false;
	private int hp;
	private static final int speed = 4;
	boolean flashing = false;
	int flashCounter = 10;
	int flashDurationCounter = 10;
	private boolean isHpdecrese=true;
	AudioClip hit1 = new AudioClip(ClassLoader.getSystemResource("bullet_hit_1.wav").toString());
	AudioClip hit2 = new AudioClip(ClassLoader.getSystemResource("bullet_hit_2.wav").toString());


	public Tank(int x, int y) {
		this.width=40;
		this.height=30;
		this.x = x;
		this.y = y;
		this.z=1;
		this.radius = 8;
		this.setImage(new Image("player_front.png"));
		this.hp=4;
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

	public void update() {
		flashState();
		checkPlaneDead();
		this.fire = false;
		addInput();
		fireTick++;
	}

	public void addInput() {
		if (InputUtility.getKeyPressed(KeyCode.LEFT)) {
			turnleft();
			this.direction=2;
			this.setImage(new Image("player_left.png"));
		}
		if (InputUtility.getKeyPressed(KeyCode.RIGHT)) {
			turnright();
			this.direction=3; 
			this.setImage(new Image("player_right.png"));
		}
		if (InputUtility.getKeyPressed(KeyCode.UP)) {
			forward();
			this.direction=0;
			this.setImage(new Image("player_front.png"));
		}
		if (InputUtility.getKeyPressed(KeyCode.DOWN)) {
			backward();
			this.direction=1;
			this.setImage(new Image("player_down.png"));
		}
		
		if (InputUtility.getKeyPressed(KeyCode.SPACE)) {
			if(fireTick>=lastFireTick) {
				fireTick=lastFireTick;
				this.fire = true;
				lastFireTick+=50;
			}
		}
	}
	public void decreaseHp() {
		if(this.isHpdecrese) {
			this.hp--;
			isHpdecrese=false;
			hit1.play();
		}
		if(hp==0) {
			hit2.play();
			this.destroyed=true;
		}
		this.flashing=true;
	}
	public void increaseHp() {
		this.hp++;
	}
	public int getHp() {
		return this.hp;
	}
	public void flashState() {
		if (flashing) {
			if (flashCounter == 0) {
				flashDurationCounter = 10;
				flashCounter = 10;
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
	private void checkPlaneDead() {
		for(IRenderable i:RenderableHolder.getInstance().getEntities()) {
			if(this.collideWith((CollidableEntity)i)){
				if((i instanceof Bullet)&&(((Bullet)i).isEnemy)){	
					((Bullet)i).destroyed=true;
					this.decreaseHp();
				}
				if(i instanceof Boss) {
					this.destroyed=true;	
				}
				if(i instanceof Bomb) {
					decreaseHp() ;
				}
				if(i instanceof Laser) {
					((Laser)i).destroyed=true;
					decreaseHp() ;
				}
				if(i instanceof Enemy) {
					decreaseHp() ;
				}
			}
		}
		if(this.hp==0) {
			hit2.play();
			this.destroyed=true;
		}
		if(fireTick%60==0) {
			isHpdecrese=true;
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setGlobalAlpha(1);
		gc.drawImage(image, x, y);
	}

}
