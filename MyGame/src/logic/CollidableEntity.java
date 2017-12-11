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
			if(other instanceof Bomb) {
				if(other.radius<0||other.destroyed==true) {
					return false;
				}
				return Math.hypot(this.x+20 - (other.x+other.width/2), this.y+15 - (other.y+other.height/2)) <= this.radius + other.radius;
			}
			if(other instanceof Laser) {
				if(((Laser)other).getLine()==6) {
					return Math.hypot(this.x + (20) - ((Laser)other).getDx(), this.y + (15) - (((Laser) other).getDy())) <= this.radius;
				}
				return Math.hypot(this.x + (20) - (other.x), this.y + (15) - (other.y)) <= this.radius;
			}
			if(other instanceof Enemy) {
				return Math.hypot(this.x+20 - (other.x+20), this.y+15 - (other.y+20)) <= this.radius + other.radius;
			}
			if(other instanceof Item) {
				//System.out.println(other.x+"     "+other.y);
				return Math.hypot(this.x+20 - (other.x+10), this.y+15 - (other.y+10)) <= this.radius + 10;
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
		
		if(this instanceof Enemy) {
			if (other instanceof Bullet) {
				for (int i = 0; i < 6; i++) {
					result = result&& Math.hypot(this.x + (20) - (other.x + i), this.y + (20) - (other.y + i)) <= this.radius;
				}
			}
			return result;
		}

		if(this instanceof Item && other instanceof Tank) {
			return Math.hypot(this.x+12-(other.x+20),this.y+12-(other.y+15)) <= other.radius + 12;
		}

		if(this instanceof Item) {
			if(other instanceof Tank) {
				//System.out.println("ssssasd");
				//System.out.println((this.x)+"      "+(other.x+20)+"     "+(this.y)+"     "+(other.y+15));
				return Math.hypot(this.x+12-(other.x+20),this.y+12-(other.y+15)) <= other.radius + 12;
			}
		}
		
		if(this instanceof Barrier) {
			if (other instanceof Bullet) {
//				System.out.print(other.x+other.width>=this.x);
//				System.out.print(other.x<=this.x+200);
//				System.out.print(other.y+other.height>=this.y);
//				System.out.println(other.y<=this.y+200);
				
				return (other.x+other.width>=this.x&&other.x<=this.x+200&&other.y+other.height>=this.y&&other.y<=this.y+200);
			}
			if(other instanceof Tank) {
				return (other.x+(other.width/2)>=this.x&&other.x+(other.width/2)<=this.x+200&&other.y+(other.height/2)>=this.y&&other.y+(other.height/2)<=this.y+200);
			}
		}
		
		return Math.hypot(this.x + (20) - other.x, this.y + (15) - other.y) <= this.radius + other.radius;
	}
}
