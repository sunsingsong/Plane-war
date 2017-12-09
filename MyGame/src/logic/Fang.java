package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fang extends CollidableEntity{
	
	public Fang(int x,int y,int no,Image image,Boss boss) {
		this.x=x;
		this.y=y;
		this.width=32;
		this.height=32;
		this.image=image;
	}
	
	public void update() {
		
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setGlobalAlpha(1);
		gc.drawImage(this.image, this.x, this.y);
		
	}
	
	
	
	
}
