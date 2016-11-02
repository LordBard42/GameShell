import java.awt.Color;
import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int levelIncrement = 1, level = 1;
	
	private float activatedWidth, activatedHeight, activatedX, activatedY;

	private Random r = new Random();

	public Spawn(Handler handler, HUD hud) {

		this.handler = handler;
		this.hud = hud;

	}

	public void tick() {
		
		

		if (level == 1) {
			
			activatedX = (float)(Game.WIDTH / 1.6);
			activatedY = (Game.HEIGHT - 300);
			activatedWidth = 100;
			activatedHeight = 15;
			
			handler.addObject(new Player(32, Game.HEIGHT - 165, ID.Player, Color.WHITE, handler));
			handler.addObject(new Floor(0, Game.HEIGHT - 100, ID.Floor, handler));
			handler.addObject(new Platform(Game.WIDTH / 3, Game.HEIGHT - 200, 100, 15, Color.red, ID.ActivatorFloor, handler));
			handler.addObject(new Platform(Game.WIDTH - 50, 200, 50, 700, Color.YELLOW, ID.Goal, handler));
			level = 0;
			levelIncrement++;
		}
		
		if (level == 2) {
			
			activatedX = 100;
			activatedY = (Game.HEIGHT - 350);
			activatedWidth = 100;
			activatedHeight = 15;
			
			handler.addObject(new Player(32, Game.HEIGHT - 165, ID.Player, Color.WHITE, handler));
			
			handler.addObject(new Platform(Game.WIDTH  - 70, 100, 20, Game.HEIGHT - 100, Color.BLUE, ID.Wall, handler));
			handler.addObject(new Platform(Game.WIDTH  - 670, 100, 600, 20,Color.BLUE, ID.Floor, handler));
			
			handler.addObject(new Floor(0, Game.HEIGHT - 100, ID.Floor, handler));
			
			handler.addObject(new Platform(Game.WIDTH - 800, Game.HEIGHT - 200, 100, 15, Color.BLUE, ID.Floor, handler));
			handler.addObject(new Platform(Game.WIDTH - 600, Game.HEIGHT - 300, 100, 15, Color.BLUE, ID.Floor, handler));
			handler.addObject(new Platform(Game.WIDTH  - 400, Game.HEIGHT - 300, 100, 15, Color.BLUE, ID.Floor, handler));
			handler.addObject(new Platform(Game.WIDTH - 200, Game.HEIGHT - 300, 100, 15, Color.red, ID.ActivatorFloor, handler));
			
			handler.addObject(new Platform(Game.WIDTH - 50, 200, 50, 700, Color.YELLOW, ID.Goal, handler));
			 
			
			
			level = 0;
			levelIncrement++;
		}
		
		
		checkForSpawns();
		checkForWin();

		
	}
	
	public void checkForWin() {

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				
				if(tempObject.getWon()){
					handler.clearAll();
					level += levelIncrement;
				}
			}

		}

	}

	public void checkForSpawns() {

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.ActivatorFloor) {

				if (tempObject.getEngaged()) {
					tempObject.setEngaged(false);
					handler.addObject(
							new Platform((int)activatedX, activatedY, activatedWidth, activatedHeight, Color.GRAY, ID.RedFloor, handler));
					System.out.println("Added");

				}

			}

		}

	}

}
