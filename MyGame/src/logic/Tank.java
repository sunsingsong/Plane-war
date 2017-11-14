package logic;

import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Tank extends CollidableEntity {

	private static final int speed = 5;
	private int angle = 0; // angle 0 = EAST

	private boolean flashing = false;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
		this.radius = 20;
	}

	private void forward() {
		this.y -= speed;
	}
	
	private void backward() {
		this.y +=  speed;
	}
	
	private void turnleft() {
		this.x -= speed;
	}
	
	private void turnright() {
		this.x += speed;
	}
 

	public void hitByMine() {
		flashing = true;
		flashCounter = 10;
		flashDurationCounter = 10;
	}

	public void update() {
		if (flashing) {
			if (flashCounter == 0) {
				this.visible = true;
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					this.visible = flashCounter <= 5;
					flashDurationCounter--;
				} else {
					this.visible = true;
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		} else {
			this.visible = !InputUtility.getKeyPressed(KeyCode.SHIFT);
		}
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			forward();
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			backward();
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			turnleft();
		}
		if (InputUtility.getKeyPressed(KeyCode.D)) {
			turnright();
		}
		
//		if (InputUtility.isLeftClickTriggered()) {
//			this.x = InputUtility.mouseX;
//			this.y = InputUtility.mouseY;
//		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		gc.fillArc(x - radius, y - radius, radius * 2, radius * 2, 0, 360, ArcType.OPEN);
		gc.translate(x, y);
		gc.rotate(angle);
		gc.setFill(Color.YELLOW);

		int gunSize = radius / 5;
		gc.fillRect(0, -gunSize, radius * 3 / 2, gunSize * 2);
		gc.rotate(-angle);
		gc.translate(-x, -y);
	}

}
