import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * World class for the game.
 * Handles initialisation, input and rendering for the objects in the world.
 */
public class World {
	private Background background = new Background("res/space.png");
	private static final float CENTERING_OFFSET = 8, SHIELDOFFSET = 2;
	private Player player = new Player();
	private Laser laser = new Laser(player.getX(), player.getY());
	private Enemies enemies = new Enemies();
	private int elapsedTime = 0, gameSpeed = 1;
	private Shield shield = new Shield(player.getX(), player.getY(), "res/shield.png", elapsedTime);
	private static final int NORMAL_SPEED = 1, SPEED_MULTIPLIER = 5;
	
	/**
	 * @throws SlickException
	 */
	public World() throws SlickException {
		// Initialise list of basicEnemies
		Enemies.init();
		Life.init();
		Shield.setActive(true);
	}
	/** Update the World class, so it reflects the current game state.
     * @param input Keyboard input object, used for controlling the player
     * @param delta Time passed since last frame (milliseconds).
     */
	public void update(Input input, int delta) throws SlickException {
		// Update all of the sprites in the game
		if (input.isKeyDown(Input.KEY_S)) {
			gameSpeed = SPEED_MULTIPLIER;
		} else {
			gameSpeed = NORMAL_SPEED;
		}
		background.update(delta*gameSpeed);
		player.update(input, delta*gameSpeed, elapsedTime);
		laser.update(input, delta*gameSpeed, player.getX() + player.getCentre() - CENTERING_OFFSET, player.getY());
		elapsedTime += delta*gameSpeed;
		enemies.update(elapsedTime, delta*gameSpeed);
		shield.update(player.getX() - player.getCentre()/SHIELDOFFSET, player.getY() - player.getCentre()/SHIELDOFFSET, delta, "res/shield.png");
		
		// Check if the player has collided with or shot any of the enemies, or if an enemy has shot the player.
		for (BasicEnemy enemy : Enemies.getEnemies()) {
			Shield.setActive(player.contactSprite(enemy));
			for (Laser currentShot : Laser.getLasers()) {
				enemy.contactSprite(currentShot, enemy);
			}
			for (EnemyLaser enemyShot : EnemyLaser.getLasers()) {
				player.contactSprite(enemyShot);
			}
		}
	}
	/**
	 * Draw all of the sprites in the game
     */
	public void render() {
		background.render();
		player.render();
		for (BasicEnemy basicEnemy : Enemies.getEnemies()) {
			basicEnemy.render();
		}
		for (Laser currentShot : Laser.getLasers()) {
			currentShot.render();
		}
		for (EnemyLaser enemyShot : EnemyLaser.getLasers()) {
			enemyShot.render();
		}
		for (Life life : Life.getLives()) {
			life.render();
		}
		shield.render(elapsedTime);
	}
	/**
	 * Getter that enables the App class to render the score
     */
	public String getScore() {
		return Integer.toString(Score.getScore());
	}
}
