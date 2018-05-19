import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * Sprite class for the game.
 * The parent abstract class for each of the sprite objects in the game world: Laser, BasicEnemy, and Player.
 * Handles sprite image rendering and provides the majority of the required variables for each object.
 */
public abstract class Sprite {
	private float x, y, speed;
	private Image sprite;
	private BoundingBox BoundingBox;
	
	/** Constructor for enemies
	 * @param x X-coordinate value
	 * @throws SlickException
	 */
	public Sprite(float x) throws SlickException {
		this.setX(x);
	}
	
	/** Overload constructor for other sprites
	 * @throws SlickException
	 */
	public Sprite() throws SlickException {
		
	}

	/** Overload constructor for laser & lives
	 * @param x X-coordinate value
	 * @param y Y-coordinate value
	 */
	public Sprite(float x, float y) {
		this.setX(x);
		this.setY(y);
	}
	/**
	 * Renders the sprite object
	 */
	public void render() {
		getSprite().draw(x, y);
	}
	/** Returns the sprite object
	 * @return
	 */
	public Image getSprite() {
		return sprite;
	}
	/** Returns the x-coordinate of the sprite object
	 * @return
	 */
	public float getX() {
		return x;
	}
	/** Sets the x-coordinate of the sprite object
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}
	/** Returns the y-coordinate of the sprite object
	 * @return
	 */
	public float getY() {
		return y;
	}
	/** Returns the y-coordinate of the sprite object
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}
	/** Returns the bounding box of the sprite object
	 * @return
	 */
	public BoundingBox getBoundingBox() {
		return BoundingBox;
	}
	/** Returns the centre dimension of the sprite object
	 * @return
	 */
	public float getCentre() {
		return getSprite().getCenterOfRotationX();
	}
	/** Returns the height dimension of the sprite object
	 * @return
	 */
	public float getHeight() {
		return getSprite().getHeight();
	}
	/** Returns the move rate of the sprite object
	 * @return
	 */
	public float getSpeed() {
		return speed;
	}
	/** Sets the image source of the sprite object
	 * @param imageSrc Image source for sprite
	 * @throws SlickException
	 */
	public void setImage(String imageSrc) throws SlickException {
		sprite = new Image(imageSrc);
		BoundingBox = new BoundingBox(sprite, x, y);
	}
}