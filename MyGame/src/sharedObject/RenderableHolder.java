package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<IRenderable> entities;

	private IRenderable boss;
	private Comparator<IRenderable> comparator;
	
	static {
		loadResource();
	}
	
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
//		mapSprite = new Image(ClassLoader.getSystemResource("Map.png").toString());
//		mineSprite = new Image(ClassLoader.getSystemResource("Mine.png").toString());
//		explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
	}
	
	public void add(IRenderable entity) {
//		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
//		for(IRenderable x: entities){
//			if(x instanceof Tank) System.out.println("tank");
//			if(x instanceof Mine) System.out.println("mine");
//			if(x instanceof Field) System.out.println("field");
//			
//		}
	}
	
	public void remove(IRenderable entity) {
		entities.remove(entity);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
	
	public IRenderable getBoss() {
		return this.boss;
	}
}
