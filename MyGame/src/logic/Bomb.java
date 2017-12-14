package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import window.SceneManager;

import java.util.Random;

public class Bomb extends CollidableEntity {
	private int tick;
	private int lastTick = 0;
	private boolean increase;
	private boolean decrease;
	private double alpha = 0.3;
	private int type;

	public Bomb(int x, int y,int hp) {
		tick = -80;
		this.radius=-20;
		this.x = new Random().nextInt(SceneManager.SCENE_WIDTH);
		this.y = new Random().nextInt(SceneManager.SCENE_HEIGHT);
		this.width = 5;
		this.height = 5;
		this.type=1;
	}
	
	public Bomb(int x, int y,Boss boss) {
		tick = -10;
		this.x = x;
		this.y = y;
		this.radius=-20;
		this.width = 5;
		this.height = 5;
		this.type=2;
	}

	public void update() {
		if(this.type==1) {
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
		if(this.type==2) {
			tick++;
			if (tick - lastTick > 1) {
				lastTick += 2;
				if(!this.decrease) {
					this.increase = true;
				}
				if (this.width < 750) {
					this.width += 30;
					this.height += 30;
					this.x -= 15;
					this.y -= 15;
				}
			}
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(this.type==1) {
			gc.setFill(Color.ORANGERED);
			if (this.increase) {
				gc.setGlobalAlpha(alpha);
				gc.fillOval(this.x, this.y, width, height);
				this.radius=width/2;
				if (this.width<100) {
					alpha += 0.01;
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
		if(this.type==2) {
			gc.setFill(Color.ORANGERED);
			if (this.increase) {
				gc.setGlobalAlpha(alpha);
				gc.fillOval(this.x, this.y, width, height);
				this.radius=width/2;
				if (this.width<750) {
					alpha += 0.01;
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
	
	

}
