package logic;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import window.SceneManager;

public class Laser extends CollidableEntity {
	private int line;
	private Laser l;
	private Laser r;
	private int direction;
	private int speed=5;
	private int playerX;
	private int playerY;
	private double xPlus;
	private double yPlus;
	private double dx;
	private double dy;
	private int no;
	

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
	
	public Laser(int x,int y,int line,int direction,int px,int py,int no) {
		this.direction = direction;
		this.dx = x;
		this.dy = y;
		this.width = 5;
		this.height = 10;
		this.line = line;
		this.playerX=px;
		this.playerY=py;
		this.speed=3;
		this.xPlus=((this.playerX-this.dx*1.0)/(50.0));
		this.yPlus=((this.playerY-this.dy*1.0)/(50.0));
		this.no=no;
	}

	public void update() {
		if(this.destroyed) {
			if(line==3) {
				this.l.destroyed=true;
				this.r.destroyed=true;
			}
			if(line==2) {
				this.l.destroyed=true;
			}
		}
		if (this.x > SceneManager.SCENE_WIDTH || this.y > SceneManager.SCENE_HEIGHT || x < 0 || y < 0) {
			this.destroyed = true;
		}
		if(line==7) {
			switch(this.direction) {
			case 1:
				this.y-=this.speed;
				break;
			case 2:
				this.y+=this.speed;
				break;
			case 3:
				this.x-=this.speed;
				break;
			case 4:
				this.x+=this.speed;
				break;
			}
		}
		if(line==8) {
			switch(this.direction) {
			case 5:
				this.y-=this.speed;
				this.x-=this.speed;
				break;
			case 6:
				this.y-=this.speed;
				this.x+=this.speed;
				break;
			case 7:
				this.y+=this.speed;
				this.x+=this.speed;
				break;
			case 8:
				this.y+=this.speed;
				this.x-=this.speed;
				break;
			}
		}
		if(line==6) {
			this.dx+=xPlus;
			this.dy+=yPlus;
			//System.out.println(xPlus+"   "+yPlus);
		}
		if(line==5) {
			int xx = new Random().nextInt(5);
			int yy = new Random().nextInt(5);
			//System.out.println(this.playerX+"   "+this.playerY);
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
		if(line==8) {
			gc.setFill(Color.RED);
			gc.fillRect(this.x, this.y, 10, 10);
		}
		if(line==7) {
			gc.setFill(Color.AQUAMARINE);
			gc.fillRect(this.x, this.y, 10, 10);
		}
		if(line==6) {
			if(this.direction==10) {
				if(this.no==1) gc.setFill(Color.PURPLE);
				if(this.no==4) gc.setFill(Color.GREEN);
				gc.fillRect(this.dx, this.dy, 10, 10);
			}
			if(this.direction==11) {
				gc.fillRect(this.dx, this.dy, 10, 10);
			}
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

	public int getLine() {
		return line;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public Laser getL() {
		return l;
	}

	public Laser getR() {
		return r;
	}
	
	
	
	

}
