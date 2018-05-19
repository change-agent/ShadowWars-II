import org.newdawn.slick.SlickException;

/**
 * SineEnemy subclass for the game.
 * Renders and handles the SineEnemy sprite according to specifications.
 */
public class SineEnemy extends BasicEnemy {
	/** Various constants used to manipulate Boss behaviour according to specifications */
	public static final float AMPLITUDE = 96, PERIOD = 1500, MOVE_RATE = 0.15f;
	/** Points value of BasicEnemy */
	public static final int POINTS_VALUE = 100;
	/** Image source for BasicEnemy sprite */
	public static final String IMAGESRC = "res/sine-enemy.png";
	private float movePos;
	
	/**
	 * @param x X-coordinate for the SineEnemy
	 * @param delay
	 * @throws SlickException
	 */
	public SineEnemy(float x, float delay) throws SlickException {
		super(x, delay);
		super.setX(x + getCentre());
		super.setImage(IMAGESRC);
		super.setNum_points(POINTS_VALUE);
	}
	@Override
	public void update(int elapsedTime, int delta) {
		double offset = AMPLITUDE*Math.sin(2*Math.PI/PERIOD*(elapsedTime - getDelay()));
		movePos = (float)(getX() + offset);
		if (elapsedTime >= super.getDelay()){
			if (getX() > movePos) {
				super.setX(getX() + MOVE_RATE*delta);
			} if (elapsedTime >= super.getDelay() && getX() < movePos) {
				super.setX(getX() - MOVE_RATE*delta);
			}
			super.setY(getY() + MOVE_RATE*delta);
			super.getBoundingBox().update(getX(), getY());
		}
	}
}