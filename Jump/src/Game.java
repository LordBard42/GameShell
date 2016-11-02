import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	/**
	 * Main General Game class
	 */
	private static final long serialVersionUID = 1490568061256700326L;

	public final static int WIDTH = 1000, HEIGHT = WIDTH / 16 * 9;

	private Thread thread;
	private boolean running = true;
	
	private Handler handler;
	private Spawn spawner;
	private HUD hud;

	public Game() {
		handler = new Handler();
		hud = new HUD();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Jump", this);
		
		spawner = new Spawn(handler, hud);
		
	}

	public synchronized void start() {

		thread = new Thread(this);
		thread.start();

	}

	public synchronized void stop() {

		try {
			thread.join();
			running = false;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() {

		
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long frames = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				tick();
				delta--;
			}
			
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
				
			}
			
			try {
				thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		stop();



	}

	private void tick() {
		
		handler.tick();
		hud.tick();
		spawner.tick();

	}

	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
			

	}
	
	public static float clamp(float var, float MAX, float MIN){
		
		if(var >= MIN)
			return MIN;
		
		else if(var <= MAX)
			return MAX;
		
		return var;
		
		
	}

	public static void main(String[] args) {
		new Game();

	}

}
