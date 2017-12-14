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
	
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
	}

	public static RenderableHolder getInstance() {
		return instance;
	}
	
	public void add(IRenderable entity) {
		entities.add(entity);
	}
	
	public void remove(IRenderable entity) {
		entities.remove(entity);
	}
	public void clear() {
		entities.clear();
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
	
}
