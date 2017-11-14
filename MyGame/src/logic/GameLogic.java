package logic;

import java.util.ArrayList;
import java.util.List;

import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	
	private Tank tank;
	
	public GameLogic(){
		this.gameObjectContainer = new ArrayList<Entity>();
	
		tank = new Tank(320,240);
		addNewObject(tank);
	}
	
	protected void addNewObject(Entity entity){
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		tank.update();
	}
}
