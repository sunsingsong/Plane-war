package menu;



import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import game.GameMain;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import window.SceneManager;

public class MainMenu extends Canvas {
	
	private static final Font MENU_FONT = new Font("Sarun's ThangLuang",100);
	
	public MainMenu() {
		
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH+100, SceneManager.SCENE_HEIGHT);
		gc.setFill(Color.WHITE);
		//gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(MENU_FONT);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = fontLoader.computeStringWidth("This is a filled Text", gc.getFont());
		gc.fillText("à¾Å¹ ÇÍÃì", (SceneManager.SCENE_WIDTH) /2, SceneManager.SCENE_HEIGHT /2);
		
		this.addKeyEventHandler();
	}
	
	private void addKeyEventHandler() {
		//TODO Fill Code
		this.setOnKeyPressed((KeyEvent event)->{
			if(event.getCode()==KeyCode.ESCAPE) {
				Platform.exit();
			}
			if(event.getCode()==KeyCode.ENTER) {
				GameMain.newGame();
			}
		});
	}
}
