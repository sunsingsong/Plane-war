package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.GameCanvas;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	private GameCanvas canvas;
	private Tank tank;
	
	public GameLogic(GameCanvas canvas){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.canvas = canvas;
		tank = new Tank(400,300);
		addNewObject(tank);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	
	public void logicUpdate(){
		for(Entity e :gameObjectContainer) {
			e.update();
		}
		if(tank.fire) {
			Bullet aBullet = new Bullet(tank.getX(),tank.getY(),tank.direction);
			addNewObject(aBullet);
		}
		canvas.paintComponent();
		
	}
}
