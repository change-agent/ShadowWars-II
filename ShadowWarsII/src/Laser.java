import java.util.ArrayList;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Laser subclass for the game.
 * Renders and handles each laser shot by generating a list of shots based on keyboard input, and then iterating through them.
 */
public class Laser extends Sprite {
	private static final float SPEED = 3;
	private static ArrayList<Laser> lasers = new ArrayList<Laser>();
	/** Image source for Laser sprite */
	public static final String IMAGESRC = "res/shot.png";
	
	/**
	 * @param x X-coordinate for the laser
	 * @param y Y-coordinate for the laser
	 * @throws SlickException
	 */
	public Laser(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(IMAGESRC);
	}
	/** Update the laser array according to the specified behaviour
	 * @param input Keyboard input object, used for shooting the laser
	 * @param delta Number of milliseconds before enemy should appear on-screen
	 * @param player_X Player's x-coordinate value
	 * @param player_Y Player's y-coordinate value
	 * @throws SlickException
	 */
	public void update(Input input, int delta, float player_X, float player_Y) throws SlickException {
		// Generate the y value for the moving laser sprite
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			Laser shot = new Laser(player_X, player_Y);
			lasers.add(shot);
		}
		// Update the BoundingBox for each laser shot
		for (Laser currentShot : lasers) {
			currentShot.setY(currentShot.getY() - SPEED*delta);
			try {
				currentShot.getBoundingBox().update(currentShot.getX(), currentShot.getY());
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}
		}
		// Remove the current laser shot from the ArrayList if it's moved off-screen
		lasers.removeIf(currentShot -> (currentShot.getY() < 0));
	}
	/** Return the ArrayList of lasers
	 * @return
	 */
	public static ArrayList<Laser> getLasers() {
		return lasers;
	}
}