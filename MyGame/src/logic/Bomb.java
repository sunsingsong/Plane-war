package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import window.SceneManager;

import java.util.Random;

public class Bomb extends CollidableEntity {
	private int bx;
	private int by;
	private int tick = -80;
	private int lastTick = 0;
	private boolean increase;
	private boolean decrease;
	private double alpha = 0.3;

	public Bomb(int x, int y,int hp) {
		this.radius=-20;
		this.bx = x;
		this.by = y;
		this.x = new Random().nextInt(SceneManager.SCENE_WIDTH);
		this.y = new Random().nextInt(SceneManager.SCENE_HEIGHT);
		this.width = 5;
		this.height = 5;
	}

	public void update() {
		tick++;
		if (tick - lastTick > 1) {
			lastTick += 2;
			if(!this.decrease) {
				this.increase = true;
			}
			if (this.width < 100) {
				this.width += 4;
				this.height += 4;
				this.x -= 2;
				this.y -= 2;
			}
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.ORANGERED);
		if (this.increase) {
			gc.setGlobalAlpha(alpha);
			gc.fillOval(this.x, this.y, width, height);
//			gc.setFill(Color.GREEN);
//			gc.fillRect(x, y, 10, 10);
			this.radius=width/2;
			if (this.width<100) {
				alpha += 0.01;
				//System.out.println(alpha);
			}else {
				this.increase = false;
				this.decrease=true;
			}
		} 
		if(decrease) {
			alpha -= 0.1;
			gc.setGlobalAlpha(alpha);
			gc.fillOval(this.x, this.y, width, height);
			if(alpha<=0.1) {
				this.destroyed=true;
				//System.out.println("asd");
			}
		}
		if(!increase&&!decrease) {
			if(tick>=-20) {
				gc.setGlobalAlpha(1);
				gc.fillOval(this.x, this.y, width, height);
			}else{
				gc.setFill(Color.GREEN);
				gc.setGlobalAlpha(1);
				gc.fillOval(this.x, this.y, width, height);
			}
			
		}

	}

}
