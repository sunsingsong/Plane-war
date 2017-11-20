package logic;



import javafx.scene.image.Image;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable{

	protected int x,y;
	protected int z;
	protected boolean visible,destroyed;
	
	protected Image image;
	protected int width;
	protected int height;
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	
//	protected void getImageDimensions() {
//        width = image.getWidth(null);
//        height = image.getHeight(null);
//    }
	
	public boolean isDestroyed(){
		return destroyed;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isVisible(){
		return visible;
	}
	
	public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
	public int getZ(){
		return z;
	}
	
	public void update() {
		this.update();
	}
	
	
}

