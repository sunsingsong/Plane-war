package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import window.SceneManager;

public class Fang extends CollidableEntity{
	
	private int no;
	boolean setup=false;
	public boolean f1;
	public boolean f2;
	public boolean f3;
	public boolean f4;
	private int hp=3;
	
	public Fang(int x,int y,int no,Image image,Boss boss) {
		this.x=x;
		this.y=y;
		this.width=32;
		this.height=32;
		this.image=image;
		this.no=no;
	}
	
	public void update() {
		switch(this.no) {
		case 1:
			if(!setup) {
				this.y--;
				if(this.y==0) {
					setup=true;
					f1=true;
				}
			}
			break;
		case 4:
			if(!setup) {
				this.y++;
				if(this.y==SceneManager.SCENE_HEIGHT-40) {
					setup=true;
					f1=true;
				}
			}
			break;
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setGlobalAlpha(1);
		gc.drawImage(this.image, this.x, this.y);
		
	}
	
	
	
	
}
