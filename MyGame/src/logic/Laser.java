package logic;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import window.SceneManager;

public class Laser extends CollidableEntity {
	private int num = 0;
	private int tick = 0;
	private int lastTick = 0;
	private int line;
	private Laser l;
	private Laser r;
	private int direction;
	private int speed=5;
	private int playerX;
	private int playerY;

	public Laser(int x, int y, int line, int direction) {
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 10;
		this.line = line;
		if (line == 3) {
			l = new Laser(x, y, 1, direction);
			r = new Laser(x, y, 1, direction);
		}
		if (line == 2) {
			l = new Laser(x, y, 4, direction);
		}
	}

	public void update() {
		if (this.x > SceneManager.SCENE_WIDTH || this.y > SceneManager.SCENE_HEIGHT || x < 0 || y < 0) {
			this.destroyed = true;
		}
		if(line==5) {
			int xx = new Random().nextInt(5);
			int yy = new Random().nextInt(5);
			if(this.x>=this.playerX) {
				if(xx>1) {
					this.x-=direction;
				}else {
					this.x+=1;
				}
			}
			if(this.x<=this.playerX) {
				if(xx>1) {
					this.x+=direction;
				}else {
					this.x-=1;
				}
			}
			if(this.y<=this.playerY) {
				if(yy>1) {
					this.y+=direction;
				}else {
					this.y-=1;
				}
			}
			if(this.y>this.playerY) {
				if(yy>1) {
					this.y-=direction;
				}else {
					this.y+=1;
				}
			}
			
		}
		if (line == 3) {
			switch (this.direction) {
			case 0:
				this.y -= speed;
				this.l.y -= speed;
				this.l.x -= 3;
				this.r.y -= speed;
				this.r.x += 3;
				break;
			case 1:
				this.y += speed;
				this.l.y += speed;
				this.l.x -= 3;
				this.r.y += speed;
				this.r.x += 3;
				break;
			}

		}
		if (line == 2) {
			switch (this.direction) {
			case 0:
				this.x += speed;
				this.l.y += 3;
				this.l.x += speed;
				break;
			case 1:
				this.x += speed;
				this.l.y -= 3;
				this.l.x += speed;
				break;
			case 2:
				this.x -= speed;
				this.l.y += 3;
				this.l.x -= speed;
				break;
			case 3:
				this.x -= speed;
				this.l.y -= 3;
				this.l.x -= speed;
				break;
			}

		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub;
		gc.setGlobalAlpha(1);
		if(this.x>=SceneManager.SCENE_WIDTH) {
			this.visible=false;
		}
		if (line==2||line==4) {
			switch (this.direction) {
			case 0:
				gc.setFill(Color.YELLOW);
				break;
			case 1:
				gc.setFill(Color.RED);
				break;
			case 3:
				gc.setFill(Color.DODGERBLUE);
				break;
			case 2:
				gc.setFill(Color.MEDIUMBLUE);
				break;
			}
			gc.fillRect(this.x, this.y, height, width);
			if(line==2) {
				this.l.draw(gc);
			}
		
		}
		if (line == 3||line==1) {
			if (this.direction == 0) {
				gc.setFill(Color.PURPLE);
			} else {
				gc.setFill(Color.GREEN);
			}
			gc.fillRect(this.x, this.y, width, height);
			if (line == 3) {
				l.draw(gc);
				r.draw(gc);
			}
		}
		if(this.line==5) {
			gc.setFill(Color.GOLD);
			gc.fillRect(this.x, this.y, 10, 10);
		}

	}
	
	public void playerPos(int x, int y) {
		this.playerX = x;
		this.playerY = y;
	}
	
	public void setDirection(int direction) {
		this.direction=direction;
	}

}
