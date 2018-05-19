import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Score class. Keeps track of the player's score and updates when they shoot an enemy.
 * Also keeps track of the enemy's "score", updating an enemy hits or shoots the player.
 *
 */
public class Score {
	private static Map<BasicEnemy, Boolean> scoreByEnemy = new HashMap<BasicEnemy, Boolean>();
	private static Map<BasicEnemy, Integer> killsByEnemyLaser = new HashMap<BasicEnemy, Integer>();
	private static Map<BasicEnemy, Boolean> killsByEnemyCollision = new HashMap<BasicEnemy, Boolean>();
	private static int score;

	/** Returns the hashmap containing the enemies that the player has not shot yet
	 * @return
	 */
	public static Map<BasicEnemy, Boolean> getScoreByEnemy() {
		return scoreByEnemy;
	}
	/** Returns the hashmap containing the enemies that have successfully shot the player
	 * @return
	 */
	public static Map<BasicEnemy, Integer> getKillsByEnemyLaser() {
		return killsByEnemyLaser;
	}
	/** Returns the hashmap containing the enemies that have collided with the player
	 * @return
	 */
	public static Map<BasicEnemy, Boolean> getKillsByEnemyCollision() {
		return killsByEnemyCollision;
	}
	/**
	 * Updates the score class (not 100% complete).
	 */
	public static void update() {
		for(Iterator<Map.Entry<BasicEnemy, Boolean>>it=scoreByEnemy.entrySet().iterator();it.hasNext();) {
			Entry<BasicEnemy, Boolean> entry = it.next();
			if (entry.getValue()) {
				setScore(getScore() + entry.getKey().getNumPoints());
				it.remove();
			}
		} 
		
		/*
		for(Iterator<Map.Entry<BasicEnemy, Integer>>it=killsByEnemyLaser.entrySet().iterator();it.hasNext();) {
			Entry<BasicEnemy, Integer> entry = it.next();
			if (entry.getValue()) {
				Life.update();
				it.remove();
			}
		}*/
		
		for(Iterator<Map.Entry<BasicEnemy, Boolean>>it=killsByEnemyCollision.entrySet().iterator();it.hasNext();) {
		     Entry<BasicEnemy, Boolean> entry = it.next();
		     if (entry.getValue()) {
		    	 Life.update();
		    	 entry.getKey().setHasCollided(!entry.getKey().getHasCollided());
		     }
		}
	}
	/** Returns the player's score
	 * @return
	 */
	public static int getScore() {
		return score;
	}
	/** Sets the player's score
	 * @param score
	 */
	public static void setScore(int score) {
		Score.score = score;
	}
}
