import org.newdawn.slick.SlickException;

/**
 * BasicShooter subclass for the game.
 * Renders and handles the BasicShooter sprite according to specifications.
 */
public class BasicShooter extends BasicEnemy {
	/** Pixel movement rate for BasicShooter */
	public static final float MOVE_RATE = 0.2f;
	/** Random number floor and ceiling values */
	public static final int RAND_MAX = 464, RAND_MIN = 48;
	/** Time in milliseconds that BasicShooter fires for */
	public static final int FIRE_TIME = 3500;
	/** Points value */
	public final int num_points = 200;
	/** Image source for BasicShooter sprite */
	public static final String IMAGESRC = "res/basic-shooter.png";
	private int rand_Y, laserTime = 0;

	/**
	 * @param x X-coordinate value for BasicShooter object
	 * @param delay Number of milliseconds before enemy should appear on-screen
	 * @throws SlickException
	 */
	public BasicShooter(float x, float delay) throws SlickException {
		super(x, delay);
		super.setImage(IMAGESRC);
		rand_Y = super.getRandomNumberInRange(RAND_MIN, RAND_MAX);
	}
	@Override
	public void update(int elapsedTime, int delta) throws SlickException {
		if (elapsedTime >= super.getDelay() && getY() < rand_Y) {
			super.setY(getY() + MOVE_RATE*delta);
		} else if (getY() >= rand_Y && laserTime == 0) {
			getLaser().shoot(getX(), getY());
			laserTime = elapsedTime;
		} else if (getY() >= rand_Y && (elapsedTime - laserTime >= FIRE_TIME)) {
			getLaser().shoot(getX(), getY());
			laserTime = elapsedTime;
		}
		getLaser().update(delta, getX(), getY());
	}
}