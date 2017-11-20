package drawing;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import window.SceneManager;
import javafx.scene.input.KeyEvent;

public class GameCanvas extends Canvas {

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

	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, getWidth(), getHeight());
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			// System.out.println(entity.getZ());
			//System.out.println(RenderableHolder.getInstance().getEntities().size());
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}

		// System.out.println("===============");
		// System.out.println("===============");

	}

}
