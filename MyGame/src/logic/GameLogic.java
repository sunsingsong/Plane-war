package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.GameCanvas;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	private GameCanvas canvas;
	private Tank tank;
	private Boss boss;
	
	public GameLogic(GameCanvas canvas){
		this.gameObjectContainer = new ArrayList<Entity>();
		this.canvas = canvas;
		tank = new Tank(400,300);
		addNewObject(tank);
		boss = new Boss(0,0);
		addNewObject(boss);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	
	public void logicUpdate(){
		for(Entity e :gameObjectContainer) {
			if(e.destroyed!=true) {
				e.update();
			}
		}
		if(tank.fire) {
			Bullet aBullet = new Bullet(tank.getX()+20,tank.getY()+15,tank.direction);
			addNewObject(aBullet);
		}
		if(boss.b1) {
			Bullet aBullet = new Bullet(boss.getX(),boss.getY(),1);
			addNewObject(aBullet);
		}
		canvas.paintComponent();
		
	}
}
