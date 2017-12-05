package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.GameCanvas;
import input.InputUtility;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	private List<Entity> graveYard;
	private GameCanvas canvas;
	private Tank tank;
	private Boss boss;
	private Bullet aBullet;
	private int upgrade = 0;
	private int count=0;
	private int lastCount=0;

	public GameLogic(GameCanvas canvas) {
		this.gameObjectContainer = new ArrayList<Entity>();
		this.graveYard = new ArrayList<Entity>();
		this.canvas = canvas;
		tank = new Tank(400, 300);
		addNewObject(tank);
		boss = new Boss(400, 0);
		addNewObject(boss);
	}

	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public void logicUpdate() {
		graveYard.clear();
		RenderableHolder.getInstance().update();
		for (Entity e : gameObjectContainer) {
			if (e.destroyed != true) {
				e.update();
			} else {
				this.graveYard.add(e);
			}

		}
		for (Entity e : graveYard) {
			gameObjectContainer.remove(e);
		}

		if (InputUtility.getKeyPressed(KeyCode.U)&&count-lastCount>50) {
			System.out.println("x");
			this.upgrade++;
			lastCount=count;
		}
		if (tank.fire) {
			aBullet = new Bullet(tank.getX() + 20, tank.getY() + 15, tank.direction, false);
			for (int i = 0; i < upgrade ; i++) {
				aBullet.upgrade();
			}
			addNewObject(aBullet);
		}
		if (boss.spawn) {
			if (boss.b1) {
				Bullet aBullet = new Bullet(boss.getX() + boss.width, boss.getY() + boss.height, boss.direction, true);
				addNewObject(aBullet);
			}
		}
		count++;
		canvas.paintComponent();

	}
}
