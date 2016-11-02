import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Set;

/**
 * This is my standard class for a generic game object...
 * @author Erich
 *
 */
public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected Color color;
	protected boolean grounded, collided, slow, engaged, stopEngaged, won;
	
	/**
	 * This is a game object which can move and has a position.
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 */
	public GameObject(float x, float y, ID id, Handler handler){
		
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Rectangle getLeftBounds();
	public abstract Rectangle getRightBounds();
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	
	public ID getID(){
		return id;
	}
	
	public void setVelX(float velX){
		this.velX = velX;
	}
	
	public float getVelX(){
		return velX;
	}
	
	public void setVelY(float velY){
		this.velY = velY;
	}
	
	public float getVelY(){
		return velY;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setCollided(boolean collided){
		this.collided = collided;
	}
	
	public boolean getCollided(){
		return collided;
	}
	
	public void setGround(boolean grounded){
		this.grounded = grounded;
		
	}
	
	public void setSlowFlag(boolean slow){
		this.slow = slow;
	}
	
	public boolean getSlowFlag(){
		return slow;
	}
	
	public void setEngaged(boolean engaged){
		this.engaged = engaged;
	}
	
	public boolean getEngaged(){
		return engaged;
	}
	
	public void setStopEngaged(boolean stopEngaged){
		this.stopEngaged = stopEngaged;
	}
	
	public boolean getStopEngaged(){
		return stopEngaged;
	}
	
	public void setWon(boolean won){
		this.won = won;
	}
	
	public boolean getWon(){
		return won;
	}
	
	
	
	

}
