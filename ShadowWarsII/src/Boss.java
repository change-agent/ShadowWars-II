import org.newdawn.slick.SlickException;

/**
 * Boss subclass for the game.
 * Renders and handles the Boss sprite
 */
public class Boss extends BasicEnemy {
	/** Various constants used to manipulate Boss behaviour according to specifications */
	public static final float MOVE_RATE_1 = 0.05f, MOVE_RATE_2 = 0.2f, MOVE_RATE_3 = 0.1f, LASER_OFFSET_1 = 97, LASER_OFFSET_2 = 74, LASER_OFFSET_RIGHT = -1;
	public static final int RAND_MAX = 896, RAND_MIN = 128, WAIT_TIME_1 = 5000, WAIT_TIME_2 = 2000, MOVE_Y = 72, FIRE_TIME = 200, FIRE_DURATION = 3000;
	/** Image source for Boss sprite */
	public static final String IMAGESRC = "res/boss.png";
	private int currTime = 0, num_points = 5000;
	private int rand_X = super.getRandomNumberInRange(RAND_MIN, RAND_MAX);

	/**
	 * @param x X-coordinate for the boss
	 * @param delay Number of milliseconds before enemy should appear on-screen
	 * @throws SlickException
	 */
	public Boss(float x, float delay) throws SlickException {
		super(x, delay);
		super.setImage(IMAGESRC);
		super.setX(x - getCentre());
		super.setY(getY() - getHeight());
	}
	@Override
	public void update(int elapsedTime, int delta) throws SlickException {
		if (elapsedTime >= super.getDelay() && getY() < MOVE_Y) {
			super.setY(getY() + MOVE_RATE_1*delta);
			currTime = elapsedTime;
		}
		if (getY() >= MOVE_Y) {
			if ((elapsedTime - currTime >= WAIT_TIME_1)) {
					if (getX() > rand_X) {
						super.setX(getX() - MOVE_RATE_2*delta);
					} else if (getX() < rand_X) {
						super.setX(getX() + MOVE_RATE_2*delta);
					}
				currTime = elapsedTime;
			}
			if ((elapsedTime - currTime >= WAIT_TIME_2)) {
				int laserStartTime = elapsedTime;
				rand_X = super.getRandomNumberInRange(RAND_MIN, RAND_MAX);
					if (getX() > rand_X) {
						super.setX(getX() - MOVE_RATE_3*delta);
						if ((elapsedTime - laserStartTime <= FIRE_DURATION)){
							fireLasers(elapsedTime, delta);
						}
					} else if (getX() < rand_X) {
						super.setX(getX() + MOVE_RATE_3*delta);
					}
				currTime = elapsedTime;
			}
		}
		getLaser().update(delta, getX(), getY());
	}
	/**
	 * @param elapsedTime Time elapsed since game commenced (milliseconds)
	 * @param delta Time passed since last frame (milliseconds)
	 * @throws SlickException
	 */
	public void fireLasers (int elapsedTime, int delta) throws SlickException {
		int currLaserTime = 0;
		if ((elapsedTime - currLaserTime >= FIRE_TIME)) {
			getLaser().shoot(getX() + getCentre() + LASER_OFFSET_1, getY() + getHeight());
			getLaser().shoot(getX() + getCentre() + LASER_OFFSET_2, getY() + getHeight());
			getLaser().shoot(getX() + getCentre() + LASER_OFFSET_1 * LASER_OFFSET_RIGHT, getY() + getHeight());
			getLaser().shoot(getX() + getCentre() + LASER_OFFSET_2 * LASER_OFFSET_RIGHT, getY() + getHeight());
			currLaserTime = elapsedTime;
		}
	}
}
