import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.IllegalFormatCodePointException;

import javax.imageio.ImageTypeSpecifier;

public class KeyInput extends KeyAdapter {

	Handler handler;
	boolean[] keyDown = new boolean[5];
	boolean jump = false;
	float tempY; // For jump

	public KeyInput(Handler handler) {

		this.handler = handler;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		keyDown[4] = false;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.id == ID.Player) {
				if (key == KeyEvent.VK_UP) {
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_DOWN) {

					
				}
				if (key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-5);
					tempObject.setSlowFlag(false);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(5);
					tempObject.setSlowFlag(false);
					keyDown[3] = true;
				} 
				if (key == KeyEvent.VK_SPACE) {
					if(tempObject.grounded ){
						tempObject.grounded = false;
						keyDown[4] = true;
						tempObject.setVelY(-12f);
					}
					
				}
				
				if(key == KeyEvent.VK_ESCAPE){
					handler.clearAll();
					
				}

			}
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (key == KeyEvent.VK_UP)
				keyDown[0] = false;
			if (key == KeyEvent.VK_DOWN)
				keyDown[1] = false;
			if (key == KeyEvent.VK_LEFT)
				keyDown[2] = false;
			if (key == KeyEvent.VK_RIGHT)
				keyDown[3] = false;
			if (key == KeyEvent.VK_SPACE)
				keyDown[4] = false;

			if (!keyDown[0] && !keyDown[1]) {

			}

			if (!keyDown[2] && !keyDown[3]) {
				tempObject.setSlowFlag(true);
			}

			if (!keyDown[4]) {

			}

		}

	}

}
