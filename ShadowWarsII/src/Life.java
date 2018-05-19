import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/**
 * Life subclass. Handles the player's life sprites based on specified game behaviour.
 *
 */
public class Life extends Sprite {
	/** Number of lives the player has */
	public final static int NUM_LIVES = 3;
	/** Number of pixels each life sprite should have between each other */
	public final static int PIXEL_GAP = 40;
	private static float x = 20, y = 696;
	/** Image source for Lives sprite */
	public final static String IMAGESRC = "res/lives.png";
	private static ArrayList<Life> lives = new ArrayList<Life>();
	
	/**
	 * @param x X-coordinate for life sprite
	 * @param y Y-coordinate for life sprite
	 * @throws SlickException
	 */
	public Life(float x, float y) throws SlickException {
		super.setX(x);
		super.setImage(IMAGESRC);
		super.setY(y);
	}
	/** Initialises array of life sprites
	 * @throws SlickException
	 */
	public static void init() throws SlickException {
		Life firstLife = new Life(x, y);
		getLives().add(firstLife);
		for (int i = 1; i < NUM_LIVES; i++) {
			Life life = new Life(x + PIXEL_GAP*i, y);
			getLives().add(life);
		}
	}
	/**
	 * Update lives according to the specification
	 */
	public static void update() {
		// If player has been shot, remove a life from the ArrayList and adjust its size accordingly
		for (int i = 0; i < lives.size(); i++) {
            int lastLife = lives.size()-1;
            lives.set(i, lives.get(lastLife));
            lives.remove(lastLife);
        }
		if (lives.size() == 0) {
			//System.exit(0);
		}
	}
	/** Return ArrayList of current lives
	 * @return
	 */
	public static ArrayList<Life> getLives() {
		return lives;
	}
}
