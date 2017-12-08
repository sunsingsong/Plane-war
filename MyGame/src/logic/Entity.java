package logic;



import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
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
	/*public Rectangle2D getBoundary()
    {
        return new Rectangle2D(x,y,width,height);
    }

    public boolean intersects(Entity e)
    {
        return e.getBoundary().intersects( this.getBoundary() );
    }*/
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

