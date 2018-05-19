import java.util.Random;
import org.newdawn.slick.SlickException;

/**
 * BasicEnemy superclass for the game.
 * Renders and updates the BasicEnemy objects in the game. Provides the basic structure for all of the other enemies.
 */
public class BasicEnemy extends Sprite {
	/** Pixel movement rate for BasicEnemy */
	public static final float MOVE_RATE = 0.2f;
	/** Points value of BasicEnemy */
	public static final int POINTS_VALUE = 50;
	/** Image source for BasicEnemy sprite */
	public static final String IMAGESRC = "res/basic-enemy.png";
	private float y = -64, delay;
	private int numPoints;
	private boolean dead = false, hasCollided = false;
	private EnemyLaser laser = new EnemyLaser(getX(), getY());
	
	/**
	 * @param x X-coordinate for the enemy
	 * @param delay Number of milliseconds before enemy should appear on-screen
	 * @throws SlickException
	 */
	public BasicEnemy(float x, float delay) throws SlickException {
		super(x);
		super.setImage(IMAGESRC);
		super.setX(x - getCentre());
		super.setY(y - getHeight());
		this.setDelay(delay);
		numPoints = POINTS_VALUE;
	}
	/** Update the BasicEnemy object location and visibility
	 * @param elapsedTime Time elapsed since game commenced (milliseconds)
	 * @param delta Time passed since last frame (milliseconds)
	 * @throws SlickException
	 */
	public void update(int elapsedTime, int delta) throws SlickException {
		if (elapsedTime >= getDelay()) {
			super.setY(getY() + MOVE_RATE*delta);
			super.getBoundingBox().update(getX(), getY());
		}
	}
	/** Checks if a laser shot has hit an enemy; if so, sets the enemy to be dead
	 * @param shot Player laser object
	 * @param basicEnemy Enemy object
	 */
	public void contactSprite(Laser shot, BasicEnemy basicEnemy) {
		try {
			if(getBoundingBox().intersects(shot.getBoundingBox()) && basicEnemy.getY() > App.SCREEN_BOUNDARY) {
				basicEnemy.setDead(true);
			}
		}
		catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}
	/** Returns whether the enemy is dead
	 * @return
	 */
	public boolean isDead() {
		return dead;
	}
	/** Sets whether the enemy is dead
	 * @param dead
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	/** Returns the enemy's appearance delay value
	 * @return
	 */
	public float getDelay() {
		return delay;
	}
	/** Sets the enemy's appearance delay value
	 * @param delay
	 */
	public void setDelay(float delay) {
		this.delay = delay;
	}
	/** Returns the enemy's laser object
	 * @return
	 */
	public EnemyLaser getLaser() {
		return laser;
	}
	/** Generates random numbers within the floor and ceiling passed in
	 * @param min Floor value for random number generation
	 * @param max Ceiling value for random number generation
	 * @return
	 */
	public int getRandomNumberInRange(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	/** Returns the points value of the enemy
	 * @return
	 */
	public int getNumPoints() {
		return numPoints;
	}
	/** Sets the points value of the enemy
	 * @param numPoints
	 */
	public void setNum_points(int numPoints) {
		this.numPoints = numPoints;
	}
	/** Returns whether enemy has collided with the player
	 * @return
	 */
	public boolean getHasCollided() {
		return hasCollided;
	}
	/** Sets whether enemy has collided with the player
	 * @param hasCollided Boolean indicating whether player collision has occurred
	 */
	public void setHasCollided(boolean hasCollided) {
		this.hasCollided = hasCollided;
	}
}