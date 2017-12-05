package logic;

public abstract class CollidableEntity extends Entity {
	protected int radius;
	boolean result;

	protected boolean collideWith(CollidableEntity other) {
		this.result=true;
		if (this instanceof Tank) {
			if (other instanceof Bullet) {
				for (int i = 0; i < 6; i++) {
					result = result&& Math.hypot(this.x + (20) - (other.x + i), this.y + (15) - (other.y + i)) <= this.radius;
				}
			}
			if(other instanceof Boss) {
				return Math.hypot(this.x+20 - (other.x+50), this.y+15 - (other.y+50)) <= this.radius + other.radius;
			}
			//System.out.println(result);
			return result;
		}
		
		if (this instanceof Boss) {
			if (other instanceof Bullet) {
				for (int i = 0; i < 6; i++) {
					result = result&& Math.hypot(this.x + (50) - (other.x + i), this.y + (50) - (other.y + i)) <= this.radius;
				}
			}
			return result;
		}
		
		return Math.hypot(this.x + (20) - other.x, this.y + (15) - other.y) <= this.radius + other.radius;
	}
}
