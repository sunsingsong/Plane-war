package drawing;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import logic.Bomb;
import logic.Boss;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;
import javafx.scene.input.KeyEvent;

public class GameCanvas extends Canvas {
	
	GraphicsContext gc = this.getGraphicsContext2D();

	public GameCanvas() {
		this.setWidth(SceneManager.SCENE_WIDTH);
		this.setHeight(SceneManager.SCENE_HEIGHT);
		this.setVisible(true);
		addListerner();
	}

	public void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});

	}

	public void paintComponent(String time) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, getWidth(), getHeight());
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			// System.out.println(entity.getZ());
			//System.out.println(RenderableHolder.getInstance().getEntities().size());
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
			
		}
		gc.setFill(Color.RED);
		gc.setFont(new Font("tahoma",50));
		gc.fillText(time, SceneManager.SCENE_WIDTH/2, SceneManager.SCENE_HEIGHT/2);
	}
	public void clearComponent() {
		gc.restore();
	}
	
}