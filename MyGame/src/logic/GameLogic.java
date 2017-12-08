package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import drawing.GameCanvas;
import input.InputUtility;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	private List<Entity> graveYard;
	private List<Enemy> enemys;
	private GameCanvas canvas;
	private Tank tank;
	private Boss boss;
	private Laser ai;
	private Enemy enemy;
	private Bullet aBullet;
	private int upgrade = 0;
	private int count = 0;
	private int lastCount = 0;
	private int count1;
	private int count2;
	boolean otk = false;

	public GameLogic(GameCanvas canvas) {
		this.gameObjectContainer = new ArrayList<Entity>();
		this.graveYard = new ArrayList<Entity>();
		this.enemys= new ArrayList<Enemy>();
		this.canvas = canvas;
		tank = new Tank(380, 300);
		addNewObject(tank);
//		boss = new Boss(355, 0);
		addNewAi();
	//	addNewObject(boss);
	//	ai = new Laser(100,100,5,2);
	//	addNewObject(ai);
	}
	protected void addNewAi() {
		for(int i=0;i<10;i++) {
			enemy = new Enemy(200+i*40,0*i*80);
			this.enemys.add(enemy);
			addNewObject(enemy);
		}
	}
	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	protected void removeObject(Entity entity) {
		gameObjectContainer.remove(entity);
		RenderableHolder.getInstance().remove(entity);
	}

	public void logicUpdate() {
		RenderableHolder.getInstance().update();
		for (Entity e : gameObjectContainer) {
			if (e.destroyed != true) {
				e.update();
			} else {
				this.graveYard.add(e);
				// System.out.println(e);
			}
		}

		// System.out.println(gameObjectContainer.size());
		for (Entity e : graveYard) {
			removeObject(e);
		}
		graveYard.clear();

		if (InputUtility.getKeyPressed(KeyCode.U) && count - lastCount > 50) {
			System.out.println("x");
			this.upgrade++;
			lastCount = count;
		}
		if (InputUtility.getKeyPressed(KeyCode.O)) {
			otk = true;
		}
		if (tank.fire) {
			aBullet = new Bullet(tank.getX() + 20, tank.getY() + 15, tank.direction, false);
			for (int i = 0; i < upgrade; i++) {
				aBullet.upgrade();
			}
			if (otk) {
				aBullet.otk();
			}
			addNewObject(aBullet);
		}

		for (Enemy enemy1 : enemys) {
			if(enemy1.fire==false)
				continue;
			Random rand = new Random();
			int randDirect = rand.nextInt(2);
			if (randDirect == 1) {
				aBullet = new Bullet(enemy1.getX() + 20, enemy1.getY() + 15, enemy1.direction, true);
				addNewObject(aBullet);
			}
		}
		
		/*if (boss.phase1) {
			if (boss.b1) {
				ai.playerPos(tank.x+tank.width/2, tank.y+tank.height/2);
				Bullet aBullet = new Bullet(boss.getX() + boss.width, boss.getY() + boss.height, boss.direction, true);
				addNewObject(aBullet);
			}
		}
		if (boss.phase2) {
			if (boss.b2) {
				ai.playerPos(tank.x+tank.width/2, tank.y+tank.height/2);
				boss.playerPos(tank.x, tank.y);
				count1 = new Random().nextInt(8);
				count2 = new Random().nextInt(8);
				// System.out.println(count1);
				Barrier barrier = new Barrier(boss);
				addNewObject(barrier);
				if (count1 == 0 || count1 == 1||count2==6||count2==1) {
					Laser laser = new Laser(boss.getX() + (boss.width / 2), boss.getY() + boss.height, 3, 1);
					addNewObject(laser);
				}
				if (count1 == 0 || count1 == 2||count2==6||count2==2) {
					Laser laser1 = new Laser(boss.getX() + (boss.width / 2), boss.getY(), 3, 0);
					addNewObject(laser1);
				}
				if (count1 == 0 || count1 == 3||count2==6||count2==3) {
					Laser laser2 = new Laser(boss.getX() + (97), boss.getY() + 74, 2, 0);
					addNewObject(laser2);
				}
				if (count1 == 0 || count1 == 4||count2==6||count2==4) {
					Laser laser3 = new Laser(boss.getX() + (97), boss.getY() + 27, 2, 1);
					addNewObject(laser3);
				}
				if (count1 == 0 || count1 == 5||count2==6||count2==5) {
					Laser laser4 = new Laser(boss.getX() + (2), boss.getY() + 76, 2, 2);
					addNewObject(laser4);
				}
				if (count1 == 0 || count1 == 6||count2==6||count2==6) {
					Laser laser5 = new Laser(boss.getX() + (2), boss.getY() + (26), 2, 3);
					addNewObject(laser5);
				}

			}
			if (boss.b3) {
				ai.setDirection(3);
				ai.playerPos(tank.x+tank.width/2, tank.y+tank.height/2);
				Barrier barrier = new Barrier(boss);
				addNewObject(barrier);
				Bomb bomb = new Bomb(boss.getX(), boss.getY(), boss.getHp());
				addNewObject(bomb);
			}
		}*/
		count++;
		canvas.paintComponent();

	}
}
