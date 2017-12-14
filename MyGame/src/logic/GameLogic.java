package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import drawing.GameCanvas;
import input.InputUtility;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import sharedObject.RenderableHolder;
import sun.misc.GC;
import window.SceneManager;

public class GameLogic {
	private List<Entity> gameObjectContainer;
	private List<Entity> graveYard;
	private List<Enemy> enemys;
	private GameCanvas canvas;
	private Tank tank;
	private Boss boss;
	private Laser ai;
	private Bullet aBullet;
	private int upgrade = 0;
	private int count = 0;
	private int lastCount = 0;
	private int tickSpawn=0;
	private int bossTickSpawn=0;
	private int lastTickSpawn=480;
	boolean otk = false;
	boolean start3 = true;
	private int countEnemy = 0;
	public int killEnemy = 0;
	public int killEnemyForItem=0;
	private boolean bossDead=true;
	private int bossCount=1;
	private int phaseBoss=1;
	public boolean end = false;
	public boolean isTerminate = false;
	public boolean isPause = false;
	public AudioClip bossBackground = new AudioClip(ClassLoader.getSystemResource("bossBackgound.wav").toString());
	public AudioClip botBackground = new AudioClip(ClassLoader.getSystemResource("stage_ost.m4a").toString());
	AudioClip shoot = new AudioClip(ClassLoader.getSystemResource("bullet_shot.wav").toString());
	AudioClip shootE = new AudioClip(ClassLoader.getSystemResource("statistics_1.wav").toString());
	AudioClip laser1 = new AudioClip(ClassLoader.getSystemResource("laser1.wav").toString());
	AudioClip bomb1 = new AudioClip(ClassLoader.getSystemResource("bomb1.wav").toString());
	AudioClip bomb2 = new AudioClip(ClassLoader.getSystemResource("bomb2.wav").toString());
	AudioClip item_sound = new AudioClip(ClassLoader.getSystemResource("item.wav").toString());


	public GameLogic(GameCanvas canvas) {
		this.gameObjectContainer = new ArrayList<Entity>();
		this.graveYard = new ArrayList<Entity>();
		this.enemys = new ArrayList<Enemy>();
		this.canvas = canvas;
		tank = new Tank(380, 300);
		addNewObject(tank);
		// boss = new Boss(355, 0);
		// addNewObject(boss);
		ai = new Laser(100, 100, 5, 2);
		addNewObject(ai);
		addNewAi();
		botBackground.play();
		//botBackground.setCycleCount(3);
		laser1.setVolume(0.5);
		bomb1.setVolume(0.3);

	}

	protected void addNewAi() {
		if(this.tickSpawn>=this.lastTickSpawn&&this.killEnemy%4==0) {
			for (int i = 0; i < 4; i++) {
				if(this.countEnemy>20) break;
				Enemy enemy = new Enemy(200 + i * 40, 0 * i * 80);
				if(this.bossDead) {
					enemy.levelEnemy=this.bossCount;
					enemy.setImage(new Image("bot"+this.bossCount+".png"));
				}
				addNewObject(enemy);
				enemys.add(enemy);
				this.countEnemy++;
			}
			this.lastTickSpawn+=600;
		}
		else if(this.tickSpawn==0) {
			for (int i = 0; i < 4; i++) {
				Enemy enemy = new Enemy(200 + i * 40, 0 * i * 80);
				addNewObject(enemy);
				enemys.add(enemy);
				this.countEnemy++;
			}
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
	public void endGame() {
		if(tank.getHp()==0) {
			end = true;
			this.laser1.stop();
			this.botBackground.stop();
			this.bossBackground.stop();
			RenderableHolder.getInstance().clear();
			this.gameObjectContainer.clear();
			InputUtility.clear();
			SceneManager.gotoEndGame();
		}
		else if(this.phaseBoss==4) {
			end = true;
			SceneManager.gotoWinnerGame();
		}
		
	}
	public void logicUpdate() {
		if(ai.destroyed) {
			ai = new Laser(5, 5, 5, 2);
			addNewObject(ai);
		}
		endGame();
		String time = "";
		tickSpawn++;
		if(this.countEnemy<20) {
			addNewAi();
		}
		time=waitBossSpawn();
		RenderableHolder.getInstance().update();
		checkEntityDead();
		itemSpawn();
		if(!bossDead)
			phaseBoss();
		secretKey();
		tankFire();
		ai.playerPos(tank.x + tank.width / 2, tank.y + tank.height / 2);
		enemyFire();
		count++;
		canvas.paintComponent(time);
	}
	private String waitBossSpawn() {
		if(this.killEnemy==20&&this.killEnemy!=0) {
			if(this.bossTickSpawn>=180) {
				botBackground.stop();
				bossSpawn();
			}
			else {
				this.bossTickSpawn++;
				String time = (3-this.bossTickSpawn/60)+"";
				return time;
			}
		}
		return "";
	}
	private void secretKey() {
		if (InputUtility.getKeyPressed(KeyCode.U) && count - lastCount > 50) {
			this.upgrade++;
			lastCount = count;
		}
		if (InputUtility.getKeyPressed(KeyCode.O)) {
			otk = true;
		}
		if (InputUtility.getKeyPressed(KeyCode.R)) {
			tank = new Tank(380, 450);
			addNewObject(tank);
		}
	}
	
	private void tankFire() {
		if (tank.fire) {
			aBullet = new Bullet(tank.getX() + 20, tank.getY() + 15, tank.direction, false);
			shoot.play();
			for (int i = 0; i < upgrade; i++) {
				aBullet.upgrade();
			}
			if (otk) {
				aBullet.otk();
			}
			addNewObject(aBullet);
		}
	}
	private void checkEntityDead() {
		for (Entity e : gameObjectContainer) {
			if (e.destroyed != true) {
				e.update();
			} else {
				if(e instanceof Laser) {
					e.update();
					this.graveYard.add(e);
				}
				if (e instanceof Enemy) {
					this.killEnemyForItem++;
					this.killEnemy++;
					enemys.remove(e);
				}
				this.graveYard.add(e);
				// System.out.println(e);
			}
		}
		for (Entity e : graveYard) {
			removeObject(e);
			if (this.enemys.contains(e)) {
				this.enemys.remove(e);
			}

		}
		this.graveYard.clear();
	}
	
	
	private void bossSpawn() {
		if (killEnemy == 20 &&this.killEnemy!=0) {
			if(this.bossDead) {
				bossBackground.play();
				bossBackground.setVolume(3);
				bossBackground.setCycleCount(3);
			    boss = new Boss(355, 0,phaseBoss);
			    addNewObject(boss);
				this.bossDead=false;
				this.bossTickSpawn=180;
			}
			if(boss.destroyed) {
				bossBackground.stop();
				botBackground.play();
				botBackground.setCycleCount(5);
				this.phaseBoss++;
				this.bossDead=true;
				this.countEnemy=0;
				this.killEnemy=0;
				this.bossCount++;
			}
			
		}
	}
	
	private void itemSpawn() {
		if(this.killEnemyForItem%2==0&&this.killEnemyForItem!=0) {
			Item item = new Item();
			addNewObject(item);
			item_sound.play();
			this.killEnemyForItem=0;
		}
	}
  
	private void enemyFire() {
		for (Enemy enemy1 : enemys) {
			if (enemy1.fire == false)
				continue;
			Random rand = new Random();
			int randDirect = rand.nextInt(2);
			if (randDirect == 1) {
				Bullet aBullet = new Bullet(enemy1.getX() + 20, enemy1.getY() + 15, enemy1.direction, true);
				shootE.play();
				addNewObject(aBullet);
			}
		}
	}
	private void phaseBoss() {
		if (boss.phase1) {
			if (boss.b1) {
				ai.playerPos(tank.x + tank.width / 2, tank.y + tank.height / 2);
				Bullet aBullet = new Bullet(boss.getX() + boss.width, boss.getY() + boss.height, boss.direction, true);
				addNewObject(aBullet);
			}
		}
		if (boss.phase2) {
			if (boss.b2) {
				ai.playerPos(tank.x + tank.width / 2, tank.y + tank.height / 2);
				boss.playerPos(tank.x, tank.y);
				int count1 = new Random().nextInt(8);
				int count2 = new Random().nextInt(8);
				// System.out.println(count1);
				if (boss.barrier) {
					Barrier barrier = new Barrier(boss);
					addNewObject(barrier);
				}
				if (count1 == 0 || count1 == 1 || count2 == 6 || count2 == 1) {
					//laser1.play();
					Laser laser = new Laser(boss.getX() + (boss.width / 2), boss.getY() + boss.height, 3, 1);
					addNewObject(laser);
					addNewObject(laser.getL());
					addNewObject(laser.getR());
				}
				if (count1 == 0 || count1 == 2 || count2 == 6 || count2 == 2) {
					//laser1.play();
					Laser laser1 = new Laser(boss.getX() + (boss.width / 2), boss.getY(), 3, 0);
					addNewObject(laser1);
					addNewObject(laser1.getL());
					addNewObject(laser1.getR());
				}
				if (count1 == 0 || count1 == 3 || count2 == 6 || count2 == 3) {
					//laser1.play();
					Laser laser2 = new Laser(boss.getX() + (97), boss.getY() + 74, 2, 0);
					addNewObject(laser2);
					addNewObject(laser2.getL());
				}
				if (count1 == 0 || count1 == 4 || count2 == 6 || count2 == 4) {
					//laser1.play();
					Laser laser3 = new Laser(boss.getX() + (97), boss.getY() + 27, 2, 1);
					addNewObject(laser3);
					addNewObject(laser3.getL());
				}
				if (count1 == 0 || count1 == 5 || count2 == 6 || count2 == 5) {
					//laser1.play();
					Laser laser4 = new Laser(boss.getX() + (2), boss.getY() + 76, 2, 2);
					addNewObject(laser4);
					addNewObject(laser4.getL());
				}
				if (count1 == 0 || count1 == 6 || count2 == 6 || count2 == 6) {
					//laser1.play();
					Laser laser5 = new Laser(boss.getX() + (2), boss.getY() + (26), 2, 3);
					addNewObject(laser5);
					addNewObject(laser5.getL());
				}

			}
			if (boss.b3) {
				//ai.setDirection(3);
				ai.playerPos(tank.x + tank.width / 2, tank.y + tank.height / 2);
				if (boss.barrier) {
					Barrier barrier = new Barrier(boss);
					addNewObject(barrier);
				}
				Bomb bomb = new Bomb(boss.getX(), boss.getY(), boss.getHp());
				addNewObject(bomb);
				//bomb1.play(0.3);
			}
		}
		if (boss.phase3) {
			if (start3) {
				
				Fang fang1 = new Fang(boss.x + (boss.width / 2) - (16), boss.y - 40, 1, new Image("fang1.png"), boss, 1);
				addNewObject(fang1);

				Fang fang2 = new Fang(boss.x - (32), boss.y, 2, new Image("fang2.png"), boss,0);
				addNewObject(fang2);

				Fang fang3 = new Fang(boss.x - (32), boss.y + (boss.height / 2) + (16), 3, new Image("fang3.png"),
						boss,0);
				addNewObject(fang3);

				Fang fang4 = new Fang(boss.x + (boss.width / 2) - (16), boss.y + 40 + boss.getHeight() - (32), 4,
						new Image("fang4.png"), boss,0);
				addNewObject(fang4);

				Fang fang5 = new Fang(boss.x + (boss.width), boss.y + (boss.height / 2) + (16), 5, new Image("fang5.png"), boss,0);
				addNewObject(fang5);

				Fang fang6 = new Fang(boss.x + (boss.width), boss.y, 6,
						new Image("fang6.png"), boss,0);
				addNewObject(fang6);
				
				boss.setFang(fang1, fang2, fang3, fang4, fang5, fang6);

				start3 = false;
			}
			
			if(boss.b5) {
				Laser l1 = new Laser(boss.fang1.x+20, boss.fang1.y+20, 6, 10, tank.x+20, tank.y+15,1);
				addNewObject(l1);
				laser1.play();
				
				Laser l4 = new Laser(boss.fang4.x+20, boss.fang4.y+20, 6, 10, tank.x+20, tank.y+15,4);
				addNewObject(l4);
				laser1.play();
				
			}
			
			if(boss.b6) {
				//System.out.println("asd");
				Laser l21 = new Laser(boss.fang2.x+20,boss.fang6.y+20,7,1);
				addNewObject(l21);
				laser1.play();
				Laser l22 = new Laser(boss.fang2.x+20,boss.fang6.y+20,7,2);
				addNewObject(l22);
				laser1.play();
				Laser l23 = new Laser(boss.fang2.x+20,boss.fang6.y+20,7,3);
				addNewObject(l23);
				laser1.play();
				Laser l24 = new Laser(boss.fang2.x+20,boss.fang6.y+20,7,4);
				addNewObject(l24);
				laser1.play();
				
				Laser l61 = new Laser(boss.fang6.x+20,boss.fang6.y+20,8,5);
				addNewObject(l61);
				laser1.play();
				Laser l62 = new Laser(boss.fang6.x+20,boss.fang6.y+20,8,6);
				addNewObject(l62);
				laser1.play();
				Laser l63 = new Laser(boss.fang6.x+20,boss.fang6.y+20,8,7);
				addNewObject(l63);
				laser1.play();
				Laser l64 = new Laser(boss.fang6.x+20,boss.fang6.y+20,8,8);
				addNewObject(l64);
				laser1.play();
			}
			
			if(boss.b7) {
				for (int i = 1; i < 4; i++) {
					Enemy e = new Enemy(200 + i * 40, 0 * i * 80);
					addNewObject(e);
					enemys.add(e);
				}
			}
			
			if(boss.bomb){
				Bomb b = new Bomb(boss.getX()+50, boss.getY()+50,boss);
				addNewObject(b);
				bomb2.play();
			}
			
			if (boss.barrier) {
				Barrier barrier = new Barrier(boss);
				addNewObject(barrier);
			}

		}
	}
	public boolean isBossDead() {
		return bossDead;
	}
	public boolean isEnd() {
		return end;
	}
	
}
