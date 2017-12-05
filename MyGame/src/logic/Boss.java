package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import window.SceneManager;

public class Boss extends CollidableEntity{
	public int direction =2;
	private int speed=3;
	private int tick=0;
	private int lastTick=0;
	boolean phase1;
	boolean b1;
	boolean phase2;
	boolean phase3;
	
	public Boss(int x, int y) {
		this.x = x;
		this.y = y;
		this.z=2;
		this.setImage(new Image("res/earth.png"));
		phase1=true;
	}
	
	public void update() {
		b1=false;
		if(phase1) {
			if(this.direction==2) {
				this.x-=speed;
				if(x<=0) {
				this.direction=3;
				}
			}
			if(this.direction==3) {
				this.x+=speed;
				if(this.x>=SceneManager.SCENE_WIDTH) {
					this.direction=2;
				}
			}
			if(tick>=lastTick) {
				tick=lastTick;
				this.phase1 = true;
				lastTick+=50;
			}
			if(tick%(this.speed*10)==0) {
				b1=true;
			}
		}
		tick++;
	}

	
	public void draw(GraphicsContext gc) {
		gc.drawImage(this.image, this.x, this.y);
	}
	
	
	
	
}
