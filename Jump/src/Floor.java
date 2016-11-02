import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Floor extends GameObject{

	
	Handler handler;
	public Floor(float x, float y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		this.handler = handler;
		
		
		
	}


	public void tick() {
		
		collision();
	}


	public void render(Graphics g) {
	
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, Game.WIDTH, 100);
		
	
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, Game.WIDTH, 100);
	}
	
	private void collision(){
		

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					
					tempObject.setY(Game.HEIGHT -163);
				

				}

			}
		}

		
	}


	
	public Rectangle getLeftBounds() {
		return new Rectangle(0, Game.HEIGHT, 0, 0);
	}


	public Rectangle getRightBounds() {
		return new Rectangle(0, Game.HEIGHT, 0, 0);
	}
	

}
