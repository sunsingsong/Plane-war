package logic;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import window.SceneManager;

public class Fang extends CollidableEntity {

	private int no;
	boolean setup = false;
	public boolean f1;
	public boolean f2;
	public boolean f3;
	public boolean f4;
	private int hp = 3;
	private int direction;
	private int speed;
	private Boss boss;
	private int tick=1;
	private int nx=-20;
	private int ny=-20;
	

	public Fang(int x, int y, int no, Image image, Boss boss, int direction) {
		this.x = x;
		this.y = y;
		this.width = 32;
		this.height = 32;
		this.image = image;
		this.no = no;
		this.direction = direction;
		this.speed = 5;
		this.boss=boss;
		
	}

	public void moveRound() {
		
		if (this.boss.getHp() == 10) {
			speed = 5;
		}
		if (this.boss.getHp() == 9) {
			this.speed = 9;
		}
		if (this.direction == 4) {
			this.x -= speed;
			if (x <= 0) {
				this.direction = 1;
			}
		}
		if (this.direction == 1) {
			this.x += speed;
			if (this.x >= SceneManager.SCENE_WIDTH - 40) {
				this.direction = 2;
			}
		}
		if (this.direction == 2) {
			this.y += speed;
			if (this.y >= SceneManager.SCENE_HEIGHT - 40) {
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

	}
	
	public void warp() {
		
		if(this.tick%100==0) {
			this.x=this.nx;
			this.y=this.ny;
			this.nx = new Random().nextInt(SceneManager.SCENE_WIDTH);
			this.ny = new Random().nextInt(SceneManager.SCENE_HEIGHT);
		}
		tick++;
	}

	public void update() {
		
		switch (this.no) {
		case 1:
			if (boss.fangSet1) {
				this.y--;
				if (this.y == 0) {
					setup = true;
					f1 = true;
				}
			}
			if (f1)
				this.moveRound();
			break;
		case 4:
			if (boss.fangSet4) {
				this.y++;
				if (this.y == SceneManager.SCENE_HEIGHT - 40) {
					setup = true;
					f1 = true;
				}
			}
			if (f1) 
				this.moveRound();
			break;
		case 2:
			if (boss.fangSet2) {
				this.x--;
				if(x==50) {
					setup=true;
					f2=true;
				}
			}
			if(f2) {
				warp();
			}
			break;
		case 6:
			if (boss.fangSet6) {
				this.x++;
				if(x==SceneManager.SCENE_WIDTH-50-40) {
					setup=true;
					f2=true;
				}
			}
			if(f2) {
				warp();
			}
			break;
		case 3:
			if (boss.fangSet3) {
				this.y-=30;
					setup=true;
					f3=true;
			}
			if(f3) {
			
			}
			break;
		case 5:
			if (boss.fangSet5) {
				this.y-=30;
				setup=true;
				f3=true;
			}
			if(f3) {
			
			}
			break;
		}
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(this.no==2) {
			gc.setGlobalAlpha(1);
			gc.drawImage(this.image, this.x, this.y);
			gc.setGlobalAlpha(0.5);
			gc.setFill(Color.AQUAMARINE);
			gc.fillOval(this.nx, this.ny, 20, 20);
			return;
		}
		if(this.no==6) {
			gc.setGlobalAlpha(1);
			gc.drawImage(this.image, this.x, this.y);
			gc.setGlobalAlpha(0.5);
			gc.setFill(Color.MEDIUMVIOLETRED);
			gc.fillOval(this.nx, this.ny, 20, 20);
			return;
		}
		gc.setGlobalAlpha(1);
		gc.drawImage(this.image, this.x, this.y);

	}

}
