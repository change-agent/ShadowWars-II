import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.SlickException;

/**
 * EnemyLaser subclass for the game.
 * Renders and handles each laser shot by generating a list of shots when called by an enemy, and then iterates through them.
 */
public class EnemyLaser extends Sprite {
	/** Pixel movement rate for EnemyLaser */
	public static final float SPEED = 0.7f;
	private static CopyOnWriteArrayList<EnemyLaser> lasers = new CopyOnWriteArrayList<EnemyLaser>();
	/** Image source for EnemyLaser sprite */
	public static final String IMAGESRC = "res/enemy-shot.png";
	
	/**
	 * @throws SlickException
	 */
	public EnemyLaser() throws SlickException {
		super.setImage(IMAGESRC);
	}
	/** Overload constructor
	 * @param x X-coordinate for the laser
	 * @param y Y-coordinate for the laser
	 * @throws SlickException
	 */
	public EnemyLaser(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(IMAGESRC);
	}
	/** Generate the moving laser sprite
	 * @param enemy_X Enemy's x-coordinate
	 * @param enemy_Y Enemy's y-coordinate
	 * @throws SlickException
	 */
	public void shoot (float enemy_X, float enemy_Y) throws SlickException {
		EnemyLaser shot = new EnemyLaser(enemy_X, enemy_Y);
		lasers.add(shot);
	}
	/** Update the laser's y-coordinate and bounding box
	 * @param delta
	 * @param enemy_X
	 * @param enemy_Y
	 * @throws SlickException
	 */
	public void update(int delta, float enemy_X, float enemy_Y) throws SlickException {
		for (EnemyLaser currentShot : lasers) {
			currentShot.setY(currentShot.getY() + SPEED*delta);
			try {
				currentShot.getBoundingBox().update(currentShot.getX(), currentShot.getY());
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}
		}
		// Remove the current laser shot from the ArrayList if it's moved off-screen
		lasers.removeIf(currentShot -> (currentShot.getY() < App.SCREEN_BOUNDARY));
	}
	/** Return array list of lasers
	 * @return
	 */
	public static CopyOnWriteArrayList<EnemyLaser> getLasers() {
		return lasers;
	}
}