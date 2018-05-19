import org.newdawn.slick.SlickException;

/**
 * Shield subclass. Used by the player upon startup of the game, and when the player loses a life.
 *
 */
public class Shield extends Sprite {
	/** Duration the shield appears for (milliseconds) */
	public static final int SHIELD_TIME = 3000;
	private static boolean active;
	
	/**
	 * @param x X-coordinate for the shield
	 * @param y Y-coordinate for the shield 
	 * @param imageSrc Image source for shield. Useful for future powerup expansion.
	 * @param elapsedTime Time elapsed since game commenced (milliseconds)
	 * @throws SlickException
	 */
	public Shield(float x, float y, String imageSrc, int elapsedTime) throws SlickException {
		super(x, y);
		super.setImage(imageSrc);
	}
	/** Update the shield position
	 * @param x X-coordinate for the shield
	 * @param y Y-coordinate for the shield
	 * @param elapsedTime Time elapsed since game commenced (milliseconds)
	 * @param imageSrc Image source for shield. Useful for future powerup expansion.
	 * @throws SlickException
	 */
	public void update(float x, float y, int elapsedTime, String imageSrc) throws SlickException {
		super.setX(x);
		super.setY(y);
	}
	/** Render the shield sprite
	 * @param elapsedTime Time elapsed since game commenced (milliseconds)
	 */
	public void render (int elapsedTime) {
		if (elapsedTime < SHIELD_TIME) {
			super.render();
			active = false;
		}
	}
	/** Returns whether the shield is active 
	 * @return
	 */
	public static boolean isActive() {
		return active;
	}
	/** Sets whether the shield is active
	 * @param active
	 */
	public static void setActive(boolean active) {
		Shield.active = active;
	}
}