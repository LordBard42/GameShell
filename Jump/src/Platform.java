import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform extends GameObject {

	Handler handler;
	protected float width, height;
	Color color;
	
	public Platform(float x, float y, float width, float height, Color color, ID id, Handler handler) {
		super(x, y, id, handler);

		this.handler = handler;
		this.width = width;
		this.height = height;
		this.engaged = false;
		this.stopEngaged = false;
		this.color = color;
		this.won = false;
	}

	public void tick() {

		collision();

	}

	public void render(Graphics g) {

		g.setColor(color);
		g.fillRect((int) x, (int) y, (int)width, (int)height);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int)width, (int)height);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y, 5,(int)height);
	}

	public Rectangle getRightBounds() {

		return new Rectangle((int)(x + width -5), (int)y, 5, (int)height);
	}

	private void collision() {

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {

					if (getLeftBounds().intersects(tempObject.getRightBounds())) 
						tempObject.setX(x - 32);

					else if(getRightBounds().intersects(tempObject.getLeftBounds()))
						tempObject.setX(x + width);
					else {
						
						
						if (tempObject.getVelY() < 0) { // Bounds Going up
							tempObject.setVelY(0);
							tempObject.setY(y + height + 1);

						}

						else if (tempObject.getVelY() > 0) { // bounds Going down
							tempObject.setVelY(0);
							tempObject.setY(y - 64);
							
							if(!getStopEngaged()){
								setEngaged(true);
								setStopEngaged(true);
							}

						}

					}

				}

			}

		}
	}

}
