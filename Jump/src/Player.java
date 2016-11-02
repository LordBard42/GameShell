import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.SynchronousQueue;

public class Player extends GameObject {

	Handler handler = new Handler();
	Color color;
	float lastY;

	public Player(float x, float y, ID id, Color color, Handler handler) {
		super(x, y, id, handler);

		this.color = color;
		this.handler = handler;
	}

	public void tick() {

		x += velX;
		y += velY;

		velY += .5;

		if (lastY + 1 < y)
			grounded = false;

		if (getSlowFlag()) {

			if (velX >= 1)
				velX /= 1.5f;

			else if (velX <= -1)
				velX /= 1.5;

			else {
				velX = 0;
				setSlowFlag(false);
			}
		}

		x = Game.clamp((int) x, 0, Game.WIDTH - 32);
		y = Game.clamp((int) y, 0, Game.HEIGHT - 53);

		lastY = y;
		collision();

	}

	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g.setColor(color);
		g.fillRect((int) x, (int) y, 32, 64);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 64);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) x, (int) y, 5, 64);
	}

	public Rectangle getRightBounds() {
		return new Rectangle((int) x + 27, (int) y, 5, 64);
	}

	private void collision() {

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() != ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {

					if (getLeftBounds().intersects(tempObject.getRightBounds()))
						break;

					else if (getRightBounds().intersects(tempObject.getLeftBounds()))
						break;
					else
						grounded = true;

				}

			}

			if (tempObject.getID() == ID.Goal) {
				if (getBounds().intersects(tempObject.getBounds())) {
					won = true;
					

				}

			}

		}

	}

}
