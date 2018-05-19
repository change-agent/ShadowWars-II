import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Player subclass for the game.
 * Renders and handles the player sprite, controlling its position based on keyboard input.
 */
public class Player extends Sprite {
	private float speed = 0.5f, x = 480, y = 688;
	/** Image source for Player sprite */
	public final static String IMAGESRC = "res/spaceship.png";
	
	public Player() throws SlickException {
		super.setX(x);
		super.setImage(IMAGESRC);
		super.setY(y);
	}
	/**
	 * @param input Keyboard input object, used for controllin the player
	 * @param delta Number of milliseconds before enemy should appear on-screen
	 * @param elapsedTime Time elapsed since game commenced (milliseconds)
	 */
	public void update(Input input, int delta, int elapsedTime) {
		// Move player according to keyboard input and update BoundingBox accordingly
		if (input.isKeyDown(Input.KEY_DOWN) && getY() <= App.SCREEN_HEIGHT - getSprite().getHeight()) {
			setY(getY() + speed*delta);
		} else if (input.isKeyDown(Input.KEY_UP) && getY() >= App.SCREEN_HEIGHT - App.SCREEN_HEIGHT) {
			setY(getY() - speed*delta);
		}
		if (input.isKeyDown(Input.KEY_RIGHT) && getX() <= App.SCREEN_WIDTH - getSprite().getWidth()) {
			setX(getX() + speed*delta);
		} else if (input.isKeyDown(Input.KEY_LEFT) && getX() >= App.SCREEN_WIDTH - App.SCREEN_WIDTH) {
			setX(getX() - speed*delta);
		}
		getBoundingBox().update(getX(), getY());
	}
	/** If player comes into contact with an enemy or enemy laser, player loses a life.
	 * @param other Enemy or enemy laser sprite that is passed in
	 * @return true if player requires a shield; false if player does not require a shield
	 */
	public boolean contactSprite(Sprite other) {
		try {
			if(getBoundingBox().intersects(other.getBoundingBox())) {
				if (!Shield.isActive()) {
					if (other instanceof BasicEnemy) {
						BasicEnemy enemy = (BasicEnemy) other;
						Score.getKillsByEnemyCollision().put(enemy, true);
					}
					return true;
				}
			}
		} 
		catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return false;
	}
}